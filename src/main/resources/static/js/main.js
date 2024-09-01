// Call the function when the page loads

const username=localStorage.getItem('userEmail');

if(username!==null){
  connectWebSocket();
}else{
  alert('Please login to access the chat');
  window.location.href = '/index';
}

function connectWebSocket() {
        // Connect to the WebSocket endpoint
        ws = new WebSocket(`ws://${window.location.host}/chat`);

        ws.onopen = () => {
            console.log('WebSocket connection opened.');
            // Send a message to register the username
            ws.send(JSON.stringify({ type: 'LOGIN', username: username }));
            document.getElementById('login').style.display = 'none';
            document.getElementById('chat').style.display = 'block';
        };

        ws.onmessage = (event) => {
            const data = JSON.parse(event.data);
            if (data.type === 'MESSAGE') {
                appendMessage(`${data.sender} to you: ${data.content}`);
            }
        };

        ws.onclose = () => {
            console.log('WebSocket connection closed.');
            alert('WebSocket connection closed.');
            document.getElementById('login').style.display = 'block';
            document.getElementById('chat').style.display = 'none';
        };

        ws.onerror = (error) => {
            console.error('WebSocket error:', error);
            alert('WebSocket error occurred.');
        };
}

function sendMessage(recipient, message) {
        const msg = {
            type: 'MESSAGE',
            recipient: recipient,
            content: message,
            sender: username
        };
        ws.send(JSON.stringify(msg));
}

function appendMessage(message) {
        const messagesDiv = document.getElementById('messages');
        const messageElement = document.createElement('div');
        messageElement.textContent = message;
        messagesDiv.appendChild(messageElement);
        messagesDiv.scrollTop = messagesDiv.scrollHeight;
}






