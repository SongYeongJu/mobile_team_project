var express = require('express');
var router = express.Router();
let socketio = require('socket.io');

let send = {};


send.connect = function () {
    let io = socketio.listen(4000);
    console.log("ready to listen");

    let Duser = new Object();
    let User = new Object();
    let Item = new Object();

    io.sockets.on('connection', function (socket) {
        console.log('connect');
        //���� ��ü�� Ŭ���̾�Ʈ host, port ���� �Ӽ����� �߰�
        //console.log(socket.id);

        socket.on('disconnect', function () {
            console.log('disconnect');
        })

        socket.on('login', function (data) {
            let info = data.split('%');
            let id = info[0];
            let pwd = info[1];
            console.log('info : ' + info);
            //db�� ���� ȹ��
            
        });

        socket.on('Uitem', function (data) {
            Item = data;
            //data�� db�� ����ϸ� ��.
        });

        socket.on('Comp', function (data) {
            //data ���� ����
            //�� ���� �̵�
            
        });

        socket.on('Refresh', function (data) {
            let x = data.x;//data���� ��ġ ���� ���� �� item�� ����
            let y = data.y;
            Duser.x = x;
            Duser.y = y;
            Item.x = x;
            Item.y = y;
            User.x = x;
            User.y = y;
            //��޿��� ��ġ�� item�� �߰�&&db&&��ü
        });

        socket.on('Dinfo', function (data) {
            socket.broadcast.emit('Dinfo', Duser, function (res) {
                console.log('��޿� structure�� user���� ����');
            });
        });
        
        socket.on('Items', function (data) {
            socket.emit('Itmes', Item, function (data) {
                console.log('��޿����� item ���� ����');
            });
        });

    });
}

module.exports = send;