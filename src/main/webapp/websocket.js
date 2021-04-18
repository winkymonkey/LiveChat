var ws;

function connect() {
    var hostname = document.location.host;
    var pathname = document.location.pathname;
    var username = document.getElementById("username").value;
    
    ws = new WebSocket("ws://" + hostname + pathname + "chat/" + username);

    ws.onmessage = function(event) {
    	var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + ": " + message.content + "\n";
    };
}

function send() {
    var content = document.getElementById("msg").value;
    var json = JSON.stringify({
        "content": content
    });
    ws.send(json);
    document.getElementById("msg").value = '';
}