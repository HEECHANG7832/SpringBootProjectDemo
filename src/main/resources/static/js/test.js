var name = prompt("입력해주세요");
console.log(name, " 님 환영합니다");

var a = true; var b = "안녕하세요"
console.log(typeof (a)); //type 확인

var height = "183.5 면 좋겠습니다";
var height_int = parseInt(height); //숫자로 시작하기만 하면 됨
var height_Float = parseFloat(height);

//객체
var man = {
    name: "홍길동", //속성 값
    age : 20,
    height : 180
}
man.name = "steve";
man["age"] = 10;

Math.pow(2, 3);
Math.sqrt(16);
Math.random();

function print(msg) {
    console.log("print");
}

var str = "Hello";
var str2 = "World";
str.concat(str2);
str + str2;
str[0];
str.charAt(str.length - 5);
str.substring(2, 2); //2번째부터 2개

//배열
var arr = [1, true, 3.14, "string", {name : "object"}];
arr.length;
arr.pop(); //맨뒤가 빠짐
arr.shift(); //맨앞이 빠짐
arr.push();
arr.unshift();
arr.reverse();
arr.sort();
arr.concat([1,2,3]);

str.split(",");

var o = {name : "object", method : print};
o.method();

//this는 호출된 함수가 어떤 객체에 바인드된 속성으로 호출되었는지 보여줌
//전역으로 선언한 함수들은 모두 window라는 객체에 바인드 된다

//클로저
function makeCounterFunction(initVal) {
    var count = initVal;
    function Increase( ) {
        count++;
        console.log(count);
    }
    return Increase;
}
var counter1 = makeCounterFunction(0);
var counter2 = makeCounterFunction(10);
counter1( ); //1
counter2( ); //11
//counter1, counter2가 가지고 있는건 Increase라는 함수 뿐
//이걸 통헤 private나 public같은 개념 구현


//프론트 엔드
//자바스크립트는 브라우저에서 API로 제공되는 추상화된 객체를 조작함으로써 웹 브라우저와 상호 작용을 합니다.
document.body.innerText = newContent;

// • window.location: 현재 브라우저의 주소를 볼 수 있습니다.
//
// • window.location.href: 현재 브라우저의 주소 창에 입력된 주소를 볼 수 있습니다. 이 값을 수정하면 입력된 주소로 페이지가 이동합니다.
//
// • window.navigator: 현재 브라우저에 관한 정보를 볼 수 있습니다.
//
// • window.screen: 현재 디스플레이의 너비와 높이 등을 볼 수 있습니다.
//
// • window.document: 현재 웹 페이지를 구성하는 HTML과 CSS가 모두 저장되어 있습니다. 이 객체를 이용하면 HTML과 CSS에 접근할 수 있습니다.

//CSS 태그의 적용순서
//HTML 태그안에 직접 적용
//head 태그안에서 style태그에 정의
//link 태그를 이용해서 외부에 정의

var t = document.getElementById("id");
t.innerHTML = "<i> hello </i>";
t.style.color = "blue";
t.style.fontStyle = "italic";

// img 태그에 대해서는 API가 src 프로퍼티(소스 속성)를 제공하지만 원래 src 속성이 제공되지 않은 태그에 대해서는 API가 src 속성을 제공하지 않기 때문입니다.
t.getAttribute("src");

document.getElementsByName();
document.getElementsByClassName();
document.getElementsByTagName();

document.querySelector("#songwriter"); //id가 songwriter
document.querySelectorAll("songwriter"); //태그 이름
document.querySelectorAll(".songwriter"); //클래스 이름이 songwriter

// • 엘리먼트를 추가하는 메서드에는 ceateElement( )와 cloneNode( )가 있습니다.
//
// • 엘리먼트를 배치하는 메서드에는 appendChild( )와 insertBefore( )가 있습니다.
//
// • 엘리먼트를 제거하는 메서드에는 removeChild( )가 있습니다.

function callback( ) { console.log("callback function is called"); };
setTimeout(callback, 3000);
setInterval(callback, 5000);
clearInterval(2); //Interval ID


//이벤트 등록 방법
var t = document.getElementById("form1");
t.onsubmit = function a( ) {
    console.log("from property");
    return false;
}

function b( ) {
    console.log("from addEventListener");
    return false;
}
t.addEventListener("submit", b); //이벤트 이름, 함수
//addEventListener은 다른 이벤트를 덮어씌우지 않는다
t.removeEventListener("submit", b);

//Ajax - Asynchronous Javascript and XMl
//페이지를 이동하지 않고 자바스크립트를 통해 HTTP요청을 보낸 후 응답 처리
var req = new XMLHttpRequest( );
req.onreadystatechange = function a( ) {
    console.log(this.readyState, this.status);//state가 변경될때 실행되는 핸들러 등록
    if (this.readyState == 4 && this.status == 200) {//요청에 대한 상태, HTTP응답 상태
        console.log(this.response);
    }
}
req.open("GET", "http://google.com");
req.send( );
// • 0: open( ) 메서드가 호출되기 전
//
// • 1: open( ) 메서드가 호출된 후
//
// • 2: 보낸 요청에 대해 응답에 헤더가 수신된 후
//
// • 3: 응답 메시지에 body 부분이 수신 중일 때
//
// • 4: 모든 응답이 완료되었을 때
req.response; //"hello AJAX"

//JSON
var obj = {
    "str": "문자열",
    "num": 3.14,
    "boolean" : true,
    "null": null,
    "undefined": undefined,
    "method": function a( ) {console.log("method") }
}
var t2 = JSON.stringify(obj); //JSON으로 변환
JSON.parse(t2); //배열로 변환

//AJax로 응답받기
var req = new XMLHttpRequest( );
req.onreadystatechange = function a( ) {
    console.log(this.readyState, this.status);
    if (this.readyState == 4) {
        console.log(this.response);
        var data = JSON.parse(this.response);                            // ➊
        for (var i in data) {                                            // ➋
            var t = document.getElementById("template".cloneNode(true)); // ➌
            t.removeAttribute("id");                                     // ➍
            t.children[0].innerText = data[i].id;                        // ➎
            t.children[1].innerText = data[i].msg;                       // ➏
            document.body.appendChild(t);                                // ➐
        }
    }
}
req.open("GET", "./json_data.txt");                                       // ➑
req.send( );

// HTML 엘리먼트를 자바스크립트에서 활용하려면 해당 엘리먼트가 스크립트보다 먼저 파싱되어야 합니다. 따라서 script가 더 아래에 있어야 합니다.

//익명함수
(function() {
    for (var i = 1; i <= 5; i++) {
        console.log(i);
    }
})();

setTimeout(
    function() {
        console.log("timeout");
    }, 3000);
