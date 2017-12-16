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
        //소켓 객체에 클라이언트 host, port 정보 속성으로 추가
        //console.log(socket.id);

        socket.on('disconnect', function () {
            console.log('disconnect');
        })

        socket.on('login', function (data) {
            let info = data.split('%');
            let id = info[0];
            let pwd = info[1];
            console.log('info : ' + info);
            //db비교 정보 획득
            
        });

        socket.on('Uitem', function (data) {
            Item = data;
            //data를 db에 등록하면 됨.
        });

        socket.on('Comp', function (data) {
            //data 정보 삭제
            //돈 정보 이동
            
        });

        socket.on('Refresh', function (data) {
            let x = data.x;//data에서 위치 정보 추출 후 item에 저장
            let y = data.y;
            Duser.x = x;
            Duser.y = y;
            Item.x = x;
            Item.y = y;
            User.x = x;
            User.y = y;
            //배달원의 위치를 item에 추가&&db&&객체
        });

        socket.on('Dinfo', function (data) {
            socket.broadcast.emit('Dinfo', Duser, function (res) {
                console.log('배달원 structure을 user에게 전송');
            });
        });
        
        socket.on('Items', function (data) {
            socket.emit('Itmes', Item, function (data) {
                console.log('배달원에게 item 정보 제공');
            });
        });

    });
}

module.exports = send;