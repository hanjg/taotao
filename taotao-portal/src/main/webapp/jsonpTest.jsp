<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>JsonpTest</title>
</head>
<body>
</body>
<script src="js/jquery-1.6.4.js" type="text/javascript"></script>
<script>

  function jsonhandle(data) {
    alert("id: " + data.id + "\n" + "name: " + data.name);
  }

  $(document).ready(function () {
    $.ajax({
      type: "get",
      url: "http://localhost:8081/rest/jsonp",
      dataType: "jsonp",  //指定服务器返回的数据类型
      jsonp: "callback",   //指定url查询参数名称
      jsonpCallback: "jsonhandle",  //指定回调函数名称
      success: function (data) {
        alert("success");
      }
    });
  })
</script>
</html>