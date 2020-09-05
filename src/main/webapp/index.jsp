<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
<h2>Hello World!</h2>
<form action="http://localhost:8080/SpringMVCAnnotation/fileupload" method="post" enctype="multipart/form-data">
    <input type="text" name="desc" value="测试文件">
    <input type="file" name="file">
    <input type="submit" value="file_submit" />
</form>
</body>
</html>
