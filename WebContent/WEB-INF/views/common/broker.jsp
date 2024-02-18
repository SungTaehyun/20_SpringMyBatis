<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<!-- 이 페이지를 broker.jsp를 만든 이유는?
 이 페이지가 어떤 기능을 중개하는 역할을 한다는 의미  -->
<script type="text/javascript">
	//컨트롤러에서 보낸 메세지가 있을 경우
	window.onload = function(e) {
		var resultMsg = '${resultMsg}';//'${resultMsg}' jstl식 표현법
		var resultCode = '${resultCode}';
		if (resultMsg.length > 0) { //한글자로도 있으면 출력해라
			alert(resultMsg);//
		}
		if (resultCode == 'ok') {
			window.location.href = '<c:url value="${nextUri}"/>';
		}

	}
</script>
<body>

</body>
</html>