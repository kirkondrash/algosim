.PHONY: clean models clients 
PROJECTS = clients models gateway repo compiler executor
CLIENTS = repo compiler executor

models/target/server-models-1.0-SNAPSHOT.jar:
	cd models && mvn install

clients/repo/target/repo-client-1.0-SNAPSHOT.jar:
	cd clients && mvn install -pl :repo-client
clients/compiler/target/compiler-client-1.0-SNAPSHOT.jar:
	cd clients && mvn install -pl :compiler-client
clients/executor/target/executor-client-1.0-SNAPSHOT.jar:
	cd clients && mvn install -pl :executor-client

clients: $(foreach client,$(CLIENTS),clients/$(client)/target/$(client)-client-1.0-SNAPSHOT.jar)

models: models/target/server-models-1.0-SNAPSHOT.jar

package-%: models clients
	cd $* && mvn package

clean-%:
	cd $* && mvn clean


clean:
	$(foreach project,$(PROJECTS),make clean-$(project);)
