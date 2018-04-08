<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String[] descContent = (String[])request.getAttribute("descContent");
	int i = 0;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta name="keywords" content="电子科技大学，皮肤病网络检测系统">
	<meta name="/x.copyright" content="版权归电子科技大学大数据研究中心所有">
	<meta name="description" content="电子科技大学皮肤病网络检测系统主页">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>检索结果</title>
	<link rel="shortcut icon" href="images/home/head.ico"/>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/result.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/jquery.circliful.min.js"></script>
</head>

<body>
  <!--页首开始-->
    <header>
    <img src="images/home/uestc2.png" alt="电子科技大学" id="uestclogo" />
    <nav id="headernav">
    <ul>
      <li>
        <a>客户端下载</a>
      </li>
      <li>
        <a>联系我们</a>
      </li>
    </ul>
    </nav>
    <div class="line"></div>
    </header>

  <!--内容开始-->
  <br><br>

  <div class="container">
    <div align="center"><a href="<%=basePath %>home"><img src="images/result/logo.png" alt="皮肤病网络检索系统" id="resultlogo"/></a></div>
    <div class="row">
       <div class="col-sm-4 col-md-4 col-lg-3">
        <p id="resulttext">您上传的图片经裁剪后是</p>
      </div>
      <div class="col-sm-3 col-md-4 col-lg-3">
        <a href="#" rel="<%=basePath%>${cropServerPath }" class="preview"><img id="imglist" class="img-rounded" src="<%=basePath%>${cropServerPath }" alt="原图像"/></a>
      </div>
    </div>   

    <br><br>
      <div class="line"></div>
    <br><br>
    <div>
       <p>检索结果:</p>
    </div>
    <section id="resultline">
	<c:forEach items="${resultList}" var="result">
		<div class="resultblock">
			<a href="<%=basePath %>servlet/detailServlet?diseaseId=${result.imageStrc.disease.id}&imgUrl=${result.cropImgUrl }" target="_blank"><img class="resultimg" src="${result.cropImgUrl }" alt="检索结果"/></a>
      		<h4>${result.imageStrc.disease.name }</h4>
      		<p class="resultinfor"><%=descContent[i++] %></p><hr>
      		<table class="simtable">
      			<tr>
      				<td><span class="simspan">相似度：</span></td>
      				<td>
      					<div style="text-align:center" class="resultdata">
        					<div id="myStat" data-dimension="50" data-text="${result.similarity }%" data-info="New Clients"  
      							data-width="6" data-fontsize="6" data-percent="${result.similarity }" data-fgcolor="#359c7e"  
      							data-bgcolor="#eee" data-fill="#ddd" style="margin:0 auto">
        					</div> 
      					</div>
 					</td>
 				</tr>
 			</table>
    	</div>
	</c:forEach>
    </section>
    <br/>
    <!--<a href="#" id="loadmore">加载更多结果</a>  -->

  </div>

  <!--页尾开始-->
    <footer>
    <section>
    <h4>
      友情链接
    </h4>
    <nav>
    <ul>
      <li>
        <a href="#">电子科技大学</a>
      </li>
      <li>
        <strong>·</strong>
      </li>
      <li>
        <a href="#">大数据研究中心</a>
      </li>
    </ul>
    </nav>
    </section>
    <section id='small'>
    <small>版权所有：电子科技大学大数据研究中心<br />地址：成都市高新区（西区）西源大道2006号主楼<br />
    </small>
    <pre>
      <small>电话：4006472998   传真：0472-2885356   邮箱：bjqchkj@163.com</small>
    </pre>
    </section>
    </footer>

</body>
</html>