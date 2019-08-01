<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
Test
</body>
<script>
window.xhr = new XMLHttpRequest();
window.buffer = new Array();
function send(value) {
  window.buffer.push(value);

    sendReally();
  
}
function sendReally() {
  window.xhr.open('POST', 'http://localhost:8080/CARTRTS/getExecutionTrace', false);
  window.xhr.send(JSON.stringify(window.buffer));
  window.buffer = new Array();
}
function addVariable(name, value) {
  if (typeof (value) == 'object') 
  {
    if (value instanceof Array) 
    {
      if (value.length > 0) 
      {
        return new Array(name, typeof (value[0]) + '_array', value);
      } else {
        return new Array(name, 'object_array', value);
      }
    }
  } else if (typeof (value) != 'undefined' && typeof (value) != 'function') 
  {
    return new Array(name, typeof (value), value);
  }
  return new Array(name, typeof (value), 'undefined');
}


function validateForm() {
  send(new Array('test.validateForm', ':::ENTER', new Array(addVariable('getUrlParameter', getUrlParameter), addVariable('status', status))));
  if ($("#account").val() == "") 
  {
    Materialize.toast('User name is null', 4000);
    send(new Array('test.validateForm', ':::EXIT11', new Array(addVariable('getUrlParameter', getUrlParameter), addVariable('status', status))));
    return false;
  }
  if ($("#password").val() == "") 
  {
    Materialize.toast('Password is null', 4000);
    send(new Array('test.validateForm', ':::EXIT15', new Array(addVariable('getUrlParameter', getUrlParameter), addVariable('status', status))));
    return false;
  }
  send(new Array('test.validateForm', ':::EXIT13', new Array(addVariable('getUrlParameter', getUrlParameter), addVariable('status', status))));
}
validateForm()


var getUrlParameter = function getUrlParameter(sParam) {
  send(new Array('test.getUrlParameter', ':::ENTER', new Array(addVariable('getUrlParameter', getUrlParameter), addVariable('i', i), addVariable('sPageURL', sPageURL), addVariable('sParam', sParam), addVariable('sParameterName', sParameterName), addVariable('sURLVariables', sURLVariables), addVariable('status', status))));
  var sPageURL = decodeURIComponent(window.location.search.substring(1)), sURLVariables = sPageURL.split('&'), sParameterName, i;
  for (i = 0; i < sURLVariables.length; i++) 
    {
      sParameterName = sURLVariables[i].split('=');
      if (sParameterName[0] === sParam) 
      {
        send(new Array('test.getUrlParameter', ':::EXIT30', new Array(addVariable('getUrlParameter', getUrlParameter), addVariable('i', i), addVariable('sPageURL', sPageURL), addVariable('sParam', sParam), addVariable('sParameterName', sParameterName), addVariable('sURLVariables', sURLVariables), addVariable('status', status))));
        return sParameterName[1] === undefined ? true : sParameterName[1];
      }
    }
  send(new Array('test.getUrlParameter', ':::EXIT26', new Array(addVariable('getUrlParameter', getUrlParameter), addVariable('i', i), addVariable('sPageURL', sPageURL), addVariable('sParam', sParam), addVariable('sParameterName', sParameterName), addVariable('sURLVariables', sURLVariables), addVariable('status', status))));
};
getUrlParameter()
var status = getUrlParameter('flag');
if (status == "fails") 
{
  Materialize.toast("Wrong user name or password", 4000);
}
</script>
</html>