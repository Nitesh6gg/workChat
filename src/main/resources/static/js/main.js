
const username=localStorage.getItem('userEmail');

let selectedUser = null; // Variable to store the selected user

if(username!==null){
  connectWebSocket();
}else{
  alert('Please login to access the chat');
  window.location.href = '/login';
}

function connectWebSocket() {
        // Connect to the WebSocket endpoint
        ws = new WebSocket(`ws://${window.location.host}/chat`);

        ws.onopen = () => {
            console.log('WebSocket connection opened.');
            // Send a message to register the username
            ws.send(JSON.stringify({ type: 'LOGIN', username: username }));
            //document.getElementById('login').style.display = 'none';
            //document.getElementById('chat').style.display = 'block';
        };

        ws.onmessage = (event) => {
           /* const data = JSON.parse(event.data);
            if (data.type === 'MESSAGE') {
                appendMessage(`${data.sender} to you: ${data.content}`);
            }*/
            const messageData = JSON.parse(event.data);
                appendMessage(messageData);
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

function loadChat(element) {
    selectedUser = element.getAttribute('data-selectedUser');
    console.log('Selected user:', selectedUser);

    if (selectedUser) {

     // Display the selected username in the HTML
            document.getElementById('selectedUserDisplay').innerText = `${selectedUser}`;

        // Your logic to load the chat for the selected user
        console.log(`Loading chat for user: ${selectedUser}`);
    } else {
        console.error('No username found for the selected user.');
    }
}

document.getElementById('sendBtn').addEventListener('click', () => {

const message = document.getElementById('messageInput').value.trim();
       console.log('send button')
        sendMessage(selectedUser, message);
        appendMessage(`You to ${selectedUser}: ${message}`);
        document.getElementById('messageInput').value = '';
    });


function sendMessage(selectedUser, message) {
console.log('sendMessage')
        const msg = {
            type: 'MESSAGE',
            recipient: selectedUser,
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

function appendMessage(messageData) {
    const chatContainer = document.getElementById('chat-container');
    if (chatContainer) {  // Check if chatContainer is not null
        const newMessageElement = document.createElement('div');
        newMessageElement.className = 'chat-message';
        newMessageElement.innerHTML = `
            <strong>${messageData.sender}</strong>: ${messageData.message}
        `;
        chatContainer.appendChild(newMessageElement);
    } else {
        console.error("Chat container not found!");
    }
}





