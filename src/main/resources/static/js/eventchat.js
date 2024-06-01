var chatPage = document.querySelector('#chat-page');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = document.querySelector('#username').value;
var eventname = document.querySelector('#eventname').value;
var userId = document.querySelector('#userId').value;
var cleaneventname = eventname.replace(/\s+/g, '').toLowerCase();
var destinationAddUser = "/app/chat.addUser/" + cleaneventname;
var destinationSendMessage = "/app/chat.sendMessage/" + cleaneventname


var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect() {
    console.log("Je suis dans connect ! ");
    chatPage.classList.remove('hidden');

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    console.log("Je suis juste avantOnconnected ! ");
    stompClient.connect({}, onConnected, onError);
}


function onConnected() {
    console.log("Je suis dans onConnected ! ");
    stompClient.subscribe('/topic/' + cleaneventname, onMessageReceived);
    console.log("Subscribe done ! ");
    stompClient.send(destinationAddUser,
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    );
    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT',
            destination: cleaneventname
        };
        stompClient.send(destinationSendMessage, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message', 'text-center', 'text-blue-100');
        messageElement.textContent = message.sender + ' a rejoin!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message', 'text-center', 'text-blue-100');
        messageElement.textContent = message.sender + ' est parti!';
    } else {
        messageElement.classList.add('chat-message', 'flex', 'items-center', 'py-3');

        var avatarElement = document.createElement('div');
        avatarElement.classList.add('flex-shrink-0');

        var imageElement = document.createElement('img');
        imageElement.setAttribute('src', '/eventz/user/profile-image/' + userId);
        imageElement.setAttribute('alt', 'Profile Picture');
        imageElement.onerror = function() {
            this.src = '/images/logo_connexion.png';
        };
        imageElement.classList.add('w-14', 'h-14', 'object-cover', 'rounded-full');

        avatarElement.appendChild(imageElement);
        messageElement.appendChild(avatarElement);

        var messageContentElement = document.createElement('div');
        messageContentElement.classList.add('ml-2');

        var usernameElement = document.createElement('span');
        usernameElement.classList.add('font-extrabold','text-blue-100');
        usernameElement.textContent = message.sender;
        messageContentElement.appendChild(usernameElement);

        var textElement = document.createElement('p');
        textElement.classList.add('text-blue-100');
        textElement.textContent = message.content;
        messageContentElement.appendChild(textElement);

        messageElement.appendChild(messageContentElement);
    }

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


connect();

messageForm.addEventListener('submit', sendMessage, true);