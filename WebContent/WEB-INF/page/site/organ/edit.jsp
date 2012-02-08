<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8" %> 
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title>机构设置</title>
	    <link rel="stylesheet" type="text/css" href='<s:url value="/ewcmssource/easyui/themes/dark-hive/easyui.css"/>' rel="stylesheet" title="dark-hive"/>
	    <link rel="stylesheet" type="text/css" href='<s:url value="/ewcmssource/easyui/themes/default/easyui.css"/>' rel="stylesheet" title="default"/>
	    <link rel="stylesheet" type="text/css" href='<s:url value="/ewcmssource/easyui/themes/pepper-grinder/easyui.css"/>' rel="stylesheet" title="pepper-grinder"/>
	    <link rel="stylesheet" type="text/css" href='<s:url value="/ewcmssource/easyui/themes/sunny/easyui.css"/>' rel="stylesheet" title="sunny"/>
        <link rel="stylesheet" type="text/css" href='<s:url value="/ewcmssource/css/ewcms.css"/>'>
        <script type="text/javascript" src='<s:url value="/ewcmssource/js/jquery.min.js"/>'></script>
        <script type="text/javascript" src='<s:url value="/ewcmssource/js/skin.js"/>'></script>
        <script type="text/javascript" src='<s:url value="/ewcmssource/easyui/jquery.easyui.min.js"/>'></script>          
	</head>
	<body>
		<s:if test="organVo==null">
			机构管理可对机构进行管理，可以创建机构、修改机构、删除机构、管理机构有关信息、管理机构站点等。
			<br>
			鼠标双击左边机构，可对机构属性信息编辑。
		</s:if>
		<s:else>
				<div class="easyui-tabs"  id="systemtab" border="false" fit="true">
					<div title="机构站点" style="padding: 5px;">
						<iframe src="<s:url action="editSite"/>?organVo.id=<s:property value="organVo.id"/>" id="editsiteifr"  name="editsiteifr" class="editifr" scrolling="no"></iframe>
					</div>					
					<div title="机构信息" style="padding: 5px;">
						<iframe src="<s:url action="editInfo"/>?organVo.id=<s:property value="organVo.id"/>" id="editinfoifr"  name="editinfoifr" class="editifr" scrolling="no"></iframe>
					</div>
					<div title="机构介绍" style="padding: 5px;">
						<iframe src="<s:url action="editIntroduce"/>?organVo.id=<s:property value="organVo.id"/>" id="editconfigifr"  name="editconfigifr" class="editifr" scrolling="no"></iframe>
					</div>	
					
				</div>						
		</s:else>			
	</body>
</html>