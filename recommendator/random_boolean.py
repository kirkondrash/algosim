from http.server import BaseHTTPRequestHandler, HTTPServer
from random import choice
from time import sleep

hostName = "0.0.0.0"
serverPort = 9000

class MyServer(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        self.wfile.write(bytes(choice(["True", "False"]), "utf-8"))

if __name__ == "__main__":
    webServer = HTTPServer((hostName, serverPort), MyServer)
    for i in range(20):
        print("Sleeping...")
        sleep(1)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")