from http.server import BaseHTTPRequestHandler, HTTPServer
from random import choice
from time import sleep

hostName = "0.0.0.0"
serverPort = 9000

current_tick = 0.00


class MyServer(BaseHTTPRequestHandler):
    def do_POST(self):
        global current_tick
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        current_tick = float(self.path.split('/')[-1])

    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        self.wfile.write(bytes(choice([
            "Hold",
            "Buy {0}".format(current_tick + 0.1),
            "Sell {0}".format(current_tick - 0.1)]), "utf-8"))


if __name__ == "__main__":
    for i in range(20):
        print("Sleeping...")
        sleep(1)

    webServer = HTTPServer((hostName, serverPort), MyServer)
    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")
