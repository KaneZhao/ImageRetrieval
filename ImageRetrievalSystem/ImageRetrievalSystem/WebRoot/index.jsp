<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String[] urlArr = new String[0];//(String[])request.getAttribute("imagesUrlArr");
%>

<!doctype html>
<html>
	<head>
	<base href="<%=basePath %>">
		<meta name="keywords" content="电子科技大学，皮肤病网络检测系统">
		<meta name="/x.copyright" content="版权归电子科技大学大数据研究中心所有">
		<meta name="description" content="电子科技大学皮肤病网络检测系统主页">

		<title>电子科技大学皮肤病网络检测系统</title>
		<link rel="shortcut icon" href="images/home/head.ico" />
		<link rel="stylesheet" href="css/base.css">
		<link rel="stylesheet" type="text/css" href="css/sudy-slider.css">
		<link rel="stylesheet" type="text/css" href="css/jquery.Jcrop.css" />
		<link rel="stylesheet" type="text/css" href="css/jquery-labelauty.css">
		<link rel="stylesheet" type="text/css" href="css/premage.min.css">
		<link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
		
		<script type="text/javascript"
			src="js/sandbox/jquery/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/sandbox/jquery/jquery.sudySlider.m.js"></script>
		<script type="text/javascript" src="js/jquery.Jcrop.js"></script>
		<script type="text/javascript" src="js/jquery-labelauty.js"></script>
		<script type="text/javascript" src="js/homePageJS.js"></script>
		<script type="text/javascript" src="js/jquery-ui.min.js"></script>
		<script type="text/javascript" src="js/premage.min.js"></script>
		<script type="text/javascript">
			var imgs = [
				<%for(int i = 0; i < urlArr.length; i++){ %>
				{
				    title:"图片示例",
					src:'<%=urlArr[i] %>'
				},
				<%} %>
			];
			function validateForm(form){
				var val = $('input:radio[name="positionFlag"]:checked').val();
				if(val == null){
					alert("请选择病变部位");
					return false;
				}else{
					var webh=document.body.scrollHeight;
					Id('waitingbox').style.display="block";
					Id("waitingbox").style.height=webh+"px";
					return true;
				}
			}
		</script>
	</head>

	<body>
		<!--页面等待动画-->
		<div id="waitingbox">
			<div id="waiting">
				<img src="images/waiting.jpg" width="50px" height="50px" id="loadpage" alt="页面加载中..." />
			</div>
		</div>
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
		<form action="<%=basePath %>servlet/searchServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm(this)">
			<div id="con">
				<section id="upimgsec">
				<img src="images/home/title2.png" alt="皮肤病网络检索系统——您身边的健康专家"
					id="titleimg" />
				<input type="button" value="上传图片" onclick="path.click()"
					id="imgchoose" />
				<input type="file" id="path" name="path" onchange="showfileinfo(this.value);"
					accept="image/*" />
				<input type="text" disabled='disabled' size="20" name="upfile"
					id="upfile" />
				<input type="button" value="提交" id="subimg" onclick="setImgPath()"/>

				<!--裁剪照片开始-->
				<div id="darkclo">
					<section id='cutfilesec'>
					<input type="button" value="x" id="closeclo" />
					<div id="cutzone">
						<p>
							请裁剪出病变区域（为了使结果更加精确，请使病变区域位于最中间）
						</p>
						<div id="cutimgbox">
							<div class="cutImg">
								<img id="targetimg" alt="上传的图片" onload="autoResize();" src=null/>
							</div>
							<div>
								<!-- 记录显示图片的宽和高-->
								<input type="hidden" id="imgWidth" name="imgWidth">
								<input type="hidden" id="imgHeight" name="imgHeight"/>
							</div>
							<div id="coords" class="coords">
								<!-- This is the form that our event handler fills -->
								<input type="hidden" id="x1" name="x1" />
								<input type="hidden" id="y1" name="y1" />
								<input type="hidden" id="x2" name="x2" />
								<input type="hidden" id="y2" name="y2" />
								<input type="hidden" id="w" name="w" />
								<input type="hidden" id="h" name="h" />
							</div>
							<section id='selbut'>
							<h4>
								部位标定
							</h4>
							<ul class="dowebok">
								<li>
									<input type="radio" name="positionFlag" data-labelauty="头部" value="0"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="脸部" value="1"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="颈部" value="2"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="上肢" value="3"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="手部" value="4"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="胸部" value="5"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="背部" value="6"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="腹部" value="7"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="腰部" value="8"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="臀部" value="9"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="生殖器" value="10"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="下肢" value="11"/>
								</li>
								<li>
									<input type="radio" name="positionFlag" data-labelauty="足部" value="12"/>
								</li>
							</ul>
							</section>
						</div>
					</div>
					<div id="preview-pane">
						<div class="preview-container">
							<img class="jcrop-preview" alt="Preview" id="preview" src=null />
						</div>
					</div>
					<input id="imgsubmit" type="submit" value="提交"/>
					</section>
				</div>
				</section>
				<br />
				<section id="imgslisec">
					<div class="line"></div>
					<div id="slider"></div>
				</section>
			</div>
		</form>
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
		<script type="text/javascript">
		//针对safari兼容性调整后的元素动画，注意不要移到其它js文件中！
    window.onload=function(){
    	myBrowser();
  	}
  function myBrowser(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
    var isOpera = userAgent.indexOf("Opera") > -1;
	    if (isOpera) {
        	return "Opera"
    	}; //判断是否Opera浏览器
    	if (userAgent.indexOf("Firefox") > -1) {
        	return "FF";
    	} //判断是否Firefox浏览器
	    if (userAgent.indexOf("Chrome") > -1){
  	return "Chrome";
 	}
    	if (userAgent.indexOf("Safari") > -1) {
        	return "Safari";
    	} //判断是否Safari浏览器
	    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
        	return "IE";
    	}; //判断是否IE浏览器
	}
	//以下是调用上面的函数
	var mb = myBrowser();
	if ("Safari" == mb) {
  	document.getElementById('path').style.display="inline";
  	document.getElementById('path').style.visibility="hidden";
  	document.getElementById('upfile').style.display='none';
	}
		</script>
	</body>
</html>