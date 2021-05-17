from http.server import BaseHTTPRequestHandler, HTTPServer

hostName = "0.0.0.0"
serverPort = 9002

LAST_100=[]
gamma = 0.1
EMA_100=[0.0]
EMA_24=[0.0]
up_trend=False
down_trend=False

def ema(window=100):
    EMA=0.0
    window=min([len(LAST_100),window])
    for val in LAST_100[-window:]:
        EMA = gamma*val + (1-gamma)*EMA
    return EMA

def sma(window=100):
    window=min([len(LAST_100),window])
    return sum(LAST_100[-window:])/window


class MyServer(BaseHTTPRequestHandler):
    def do_POST(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        global up_trend, down_trend
        param=self.path.split('/')[-1]
        cur = float(param)
        LAST_100.append(cur)
        if len(LAST_100)>100:
            LAST_100.pop(0)
        EMA_100.append(sma())
        EMA_24.append(sma(24))
        up_trend=(EMA_24[-1] > EMA_100[-1] and EMA_24[-2] < EMA_100[-2])
        down_trend=(EMA_24[-1] < EMA_100[-1] and EMA_24[-2] > EMA_100[-2])



    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        if up_trend:
            self.wfile.write(bytes("Buy {0}".format(LAST_100[-1]+0.1), "utf-8"))
            return
        if down_trend:
            self.wfile.write(bytes("Sell {0}".format(LAST_100[-1]-0.1), "utf-8"))
            return
        self.wfile.write(bytes("Hold", "utf-8"))

if __name__ == "__main__":
    webServer = HTTPServer((hostName, serverPort), MyServer)

    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")