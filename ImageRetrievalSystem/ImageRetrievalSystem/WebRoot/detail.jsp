<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String[] similarImgUrls = (String[])request.getAttribute("similarImgUrls");
	String imgUrl = (String)request.getAttribute("imgUrl");
%>

<!doctype html>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta name="keywords" content="电子科技大学，皮肤病网络检测系统">
		<meta name="/x.copyright" content="版权归电子科技大学大数据研究中心所有">
		<meta name="description" content="电子科技大学皮肤病网络检测系统主页">

		<title>疾病详细</title>
		<link rel="shortcut icon" href="images/home/head.ico" />
		<link rel="stylesheet" href="css/detail2.css">
		<script type="text/javascript" src="js/sandbox/jquery/jquery-1.8.3.min.js"></script>
		<!--回到顶部js代码-->
		<script type="text/javascript">
    		$(function(){
    	        var bot1=document.getElementById("form_top");
        		var bot2=document.getElementById("float_catalog");
        	$(window).scroll(function(){
        		var y=document.body.scrollHeight-document.documentElement.clientHeight-198;
        		if($(window).scrollTop()>=y){
        			var s=$(window).scrollTop()-y;
        			bot1.style.bottom=24+s+"px";
        			bot2.style.bottom=90+s+"px";
        		}
        		else{
        			bot1.style.bottom="24px";
        			bot2.style.bottom="90px";
        		}
            	if($(window).scrollTop()> 100){
                	$("#form_top").fadeIn(500);//半秒渐入动画
                	$("#float_catalog").fadeIn(500);
            	}else{
                	$("#form_top").fadeOut(500);//半秒渐隐动画
                	$("#float_catalog").fadeOut(500);
            	}
        	});
         
	        $("#form_top").click(function(){
	            $('body,html').animate({scrollTop:0},500);
	        });
    });
		</script>
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
		<div id="con">
			<!--下面是定义列表演示-->
				<dl>
  					<dt>${disease.name }</dt>
  					<img src="<%=imgUrl %>" alt="${disease.name }" id="dermimg1" />
    			</dl>
			<table id="catalog">
				<td rowspan="8">目录</td>
  				<td><a href="javascript:void(0)" onclick="document.getElementById('desc').scrollIntoView();" class="list-con">1&nbsp;疾病简介</a></td>
  				<td><a href="javascript:void(0)" onclick="document.getElementById('symptom').scrollIntoView();" class="list-con">2&nbsp;典型症状</a></td>
  				<td><a href="javascript:void(0)" onclick="document.getElementById('etiology').scrollIntoView();" class="list-con">3&nbsp;发病原因</a></td>
  				<td><a href="javascript:void(0)" onclick="document.getElementById('prevent').scrollIntoView();" class="list-con">4&nbsp;预防</a></td>
  				</tr>
  				<tr>
  				<td><a href="javascript:void(0)" onclick="document.getElementById('identify').scrollIntoView();" class="list-con">5&nbsp;鉴别</a></td>
  				<td><a href="javascript:void(0)" onclick="document.getElementById('therapies').scrollIntoView();" class="list-con">6&nbsp;治疗方法</a></td>
  				<td><a href="javascript:void(0)" onclick="document.getElementById('complication').scrollIntoView();" class="list-con">7&nbsp;并发症</a></td>
  				<td><a href="javascript:void(0)" onclick="document.getElementById('imgs').scrollIntoView();" class="list-con">8&nbsp;相似照片</a></td>
			</table>
			<div class="line"></div>
			<section>
				<a id="desc">疾病简介</a>
					${disease.desc }
				<div class="line"></div>
			</section>
			<section>
				<a id="symptom">典型症状</a>
					${disease.symptom }
				<div class="line"></div>
			</section>
			<section>
				<a id="etiology">发病原因</a>
					${disease.etiology }
				<div class="line"></div>
			</section>
			<section>
				<a id="prevent">预防</a>
					${disease.prevent }
				<div class="line"></div>
			</section>
			<section>
				<a id="identify">鉴别</a>
					${disease.identify }
				<div class="line"></div>
			</section>
			<section>
				<a id="therapies">治疗方法</a>
					${disease.therapies }
				<div class="line"></div>
			</section>
			<section>
				<a id="complication">并发症</a>
					${disease.complication }
				<div class="line"></div>
			</section>
			<section>
				<a id="imgs">相似照片</a>
				<br/>
				<div id="photos">
					<c:forEach items="<%=similarImgUrls %>" var="similarImgUrl">
	  					<img src="${similarImgUrl }" alt="相似图片" class="dermimg2" />
	  				</c:forEach>
	  			</div>
			</section>
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
    <div id="float_catalog">
    	<ul>
    		<li><a href="javascript:void(0)" onclick="document.getElementById('desc').scrollIntoView();" class="list-con">1&nbsp;疾病简介</a></li>
    		<li><a href="javascript:void(0)" onclick="document.getElementById('symptom').scrollIntoView();" class="list-con">2&nbsp;典型症状</a></li>
    		<li><a href="javascript:void(0)" onclick="document.getElementById('etiology').scrollIntoView();" class="list-con">3&nbsp;发病原因</a></li>
    		<li><a href="javascript:void(0)" onclick="document.getElementById('prevent').scrollIntoView();" class="list-con">4&nbsp;预防</a></li>
    		<li><a href="javascript:void(0)" onclick="document.getElementById('identify').scrollIntoView();" class="list-con">5&nbsp;鉴别</a></li>
    		<li><a href="javascript:void(0)" onclick="document.getElementById('therapies').scrollIntoView();" class="list-con">6&nbsp;治疗方法</a></li>
    		<li><a href="javascript:void(0)" onclick="document.getElementById('complication').scrollIntoView();" class="list-con">7&nbsp;并发症</a></li>
    		<li><a href="javascript:void(0)" onclick="document.getElementById('imgs').scrollIntoView();" class="list-con">8&nbsp;相似照片</a></li>
		</ul>
    </div>
    <div id="form_top"></div>
	</body> 
</html>
