from http.server import BaseHTTPRequestHandler, HTTPServer

hostName = "0.0.0.0"
serverPort = 9001


def determine_trend():
    with open('next_day.csv') as f:
        max = 0.0
        min = 1000.0
        cur_position = 0
        max_position = 0
        min_position = 0
        for line in f:
            num = float(line)
            if num > max:
                max = num
                max_position = cur_position
            if num < min:
                min = num
                min_position = cur_position
            cur_position += 1
    trend= {"max": max,
            "min": min,
            "is_up_trend": max_position > min_position,
            "delta": abs(max - min) * 0.2}
    return trend


TREND = determine_trend()
current_tick=0.00


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


        if current_tick > (TREND['max'] - TREND['delta']) and not TREND['is_up_trend']:
            self.wfile.write(bytes("Sell {0}".format(TREND['min'] + TREND['delta']), "utf-8"))
            return
        if current_tick < (TREND['min'] + TREND['delta']) and TREND['is_up_trend']:
            self.wfile.write(bytes("Buy {0}".format(TREND['max'] - TREND['delta']), "utf-8"))
            return
        self.wfile.write(bytes("Hold", "utf-8"))
        return


if __name__ == "__main__":
    webServer = HTTPServer((hostName, serverPort), MyServer)

    print("Server started http://%s:%s" % (hostName, serverPort))

    try:
        webServer.serve_forever()
    except KeyboardInterrupt:
        pass

    webServer.server_close()
    print("Server stopped.")
