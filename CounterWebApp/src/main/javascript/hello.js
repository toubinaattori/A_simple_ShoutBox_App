import $ from 'jquery';
import 'whatwg-fetch'; 


document.getElementById("kakka").addEventListener("click", () => {
        if($(".shoutbox-form .kakkakikkare ").css("display") === 'none'){
            $(".shoutbox-form .kakkakikkare").css({"display": "block"});
        }
        else{
            $(".shoutbox-form .kakkakikkare").css({"display": "none"});
        }
});

function getDefinitions(){
    return new Promise(function(resolve){
        resolve(ContactManager.request("definition:entities"));
        });
    }

window.onload = function() {
    let ul = $('ul.shoutbox-content');
    fetch("./rest/shout/load",{
        method: 'GET'
    })
    .then((resp) => resp.json())
    .then((data) => {
        for(var i = data.length-1; i >= 0; i--)
        {
            let nickname = data[i].nickname;
            let content = data[i].content;
            ul.prepend('<li>'+
                '<span class="shoutbox-username">' + nickname + '</span>'+
                '<p class="shoutbox-comment">' + content + '</p>'+
                
            '</li>');
        }
   });
           
}



document.getElementById("myBtn").addEventListener("click", () => {
   let ul = $('ul.shoutbox-content');
   let nickname = $("#shoutbox-name").val();
   let content = $("#shoutbox-comment").val();
   let today = new Date();
   let h = today.getHours();
   let m = today.getMinutes();
   let s = today.getSeconds();
   
   ul.prepend('<li>'+
           '<span class="shoutbox-username">' + nickname + '</span>'+
           '<p class="shoutbox-comment">' + content + '</p>'+
           
           '<span class="shoutbox-comment-ago">' + h+':'+m +':'+s+ '</span>'+
   '</li>');
   let myHeaders = new Headers();
   myHeaders.append('nickname',nickname);
   myHeaders.append('content',content);
   fetch("./rest/shout/add",{
       method: 'POST',
       headers: myHeaders
   })
   .then((response) => {
       if (response.status === 200) {
            console.log("Response ok");
         return;
       }

       else{
        console.log("Response not ok");}
    
     }
   )
   .catch((err) => {console.log('Exception: ' + err)});
});



