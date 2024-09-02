
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
/*
function loadChat(username) {
 console.log('Loading chat for user:', username);



//selectedUser = document.getElementById('selectedUser');
    selectedUser = username; // Set the selected user
//    document.getElementById('login').style.display = 'none';
//    document.getElementById('chat').style.display = 'block';
// Assuming 'item' is defined and has a 'username' property


console.log("Selected user is", selectedUser);
// Update chat section with selected user's details
    const chatHeaderUsername = document.getElementById('chat-header-username');
    if (chatHeaderUsername) {
        chatHeaderUsername.textContent = selectedUser;
    }

    // Update chat section with selected user's details
    //document.getElementById('chat-header-username')?.textContent = username;
    // You can also set other user-specific data here if needed

    // If WebSocket is already open, send a message to join the chat with the selected user
    if (ws && ws.readyState === WebSocket.OPEN) {
    console.log("selected user is ",selectedUser);
    }
        //ws.send(JSON.stringify({ type: 'JOIN', username: selectedUser }));
    else {
        // Reconnect WebSocket if it's not open
        connectWebSocket();
    }
}*/

// chat.js

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
        appendMessage(`You to ${recipient}: ${message}`);
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






