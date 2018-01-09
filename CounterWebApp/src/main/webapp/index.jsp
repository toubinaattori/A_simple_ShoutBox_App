<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
     <div class="shoutbox">
            
            <h1>Shout box</h1>
            
            <ul class="shoutbox-content" style="width: 474px; max-height: 200px; overflow: auto"></ul>
            
            <div class="shoutbox-form">
                <h2 id="kakka">Write a message <span>�</span></h2>
                
                <div class="kakkakikkare">
                    <label for="shoutbox-name">nickname </label> <input type="text" id="shoutbox-name" name="name"/>
                    <label class="shoutbox-comment-label" for="shoutbox-comment">message </label> <textarea id="shoutbox-comment" name="comment" maxlength='240'></textarea>
                    <button type="button" id="myBtn">Shout!</button>
                </div>
            </div>
            
        </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
 <script src="javascript/hello.js"></script> 
</html>