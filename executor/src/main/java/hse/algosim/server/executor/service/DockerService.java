package hse.algosim.server.executor.service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Network;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import hse.algosim.server.model.ModelToAlgo;
import hse.algosim.server.model.RecommendationModel;
import hse.algosim.server.model.SrcMeta;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class DockerService {
    private DockerClient dockerClient;
    private String networkId;

    public DockerService() {
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();

        DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
                .build();
        dockerClient = DockerClientImpl.getInstance(config, httpClient);

        for (Network network : dockerClient.listNetworksCmd().exec()) {
            if (network.getName().equals("algosim_default")) {
                networkId = network.getId();
                break;
            }
        }
    }

    public ModelToAlgo runImage(ModelToAlgo modelToAlgo) throws IOException {
        RecommendationModel model = modelToAlgo.getModel();
        ExposedPort exposedPort = ExposedPort.parse(model.getContainerPort());
        Ports portBindings = new Ports();
        Integer assignedHostPort = findRandomOpenPortOnAllLocalInterfaces();
        portBindings.bind(exposedPort, Ports.Binding.bindPort(assignedHostPort));
        CreateContainerCmd createContainerCmd = dockerClient
                .createContainerCmd(model.getName())
                .withHostConfig(new HostConfig().withPortBindings(portBindings));
        if (model.getAdditionalArgs()!=null){
            createContainerCmd.withCmd(model.getAdditionalArgs().split(" "));
        }
        CreateContainerResponse container = createContainerCmd.exec();

        dockerClient.connectToNetworkCmd().withContainerId(container.getId()).withNetworkId(networkId).exec();
        dockerClient.startContainerCmd(container.getId()).exec();
        return modelToAlgo.toBuilder().containerId(container.getId()).hostPort(assignedHostPort).build();
    }

    public SrcMeta runImages(SrcMeta srcMeta) throws IOException {
        Set<ModelToAlgo> updatedModelsToAlgo = new HashSet<>();
        for (ModelToAlgo modelToAlgo : srcMeta.getModels()) {
            ModelToAlgo toAlgo = runImage(modelToAlgo);
            updatedModelsToAlgo.add(toAlgo);
        }
        return srcMeta.toBuilder().models(updatedModelsToAlgo).build();
    }

    public void killContainer(String containerId){
        dockerClient.killContainerCmd(containerId).exec();
        dockerClient.removeContainerCmd(containerId).exec();
    }

    public void killModels(SrcMeta srcMeta){
        srcMeta.getModels()
                .stream()
                .map(ModelToAlgo::getContainerId)
                .forEach(this::killContainer);
    }

    public boolean isContainerHealthy(String containerId){
        return dockerClient.inspectContainerCmd(containerId).exec().getState().getHealth().getStatus().equals("healthy");
    }

    public boolean isModelSetHealthy(SrcMeta srcMeta){
        return srcMeta.getModels().stream().map(ModelToAlgo::getContainerId).allMatch(this::isContainerHealthy);
    }

    public void waitForModelBoot(SrcMeta srcMeta) throws InterruptedException {
        if (!srcMeta.getModels().isEmpty()){
            for (int i = 0; i < 30; i++) {
                if (isModelSetHealthy(srcMeta)){
                    break;
                }
                Thread.sleep(1000);
            }
            if (!isModelSetHealthy(srcMeta)){
                throw new RuntimeException("Recommendation models could not be started!");
            }
        }
    }

    private Integer findRandomOpenPortOnAllLocalInterfaces() throws IOException {
        try (
                ServerSocket socket = new ServerSocket(0);
        ) {
            return socket.getLocalPort();
        }
    }

}
