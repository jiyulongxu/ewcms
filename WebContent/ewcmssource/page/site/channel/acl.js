/*!
 * Copyright (c)2010-2011 Enterprise Website Content Management System(EWCMS), All rights reserved.
 * EWCMS PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * http://www.ewcms.com
 */

var ChannelAcl = function(urls){
    ewcmsBOBJ = new EwcmsBase();
    ewcmsOOBJ = new EwcmsOperate();
    this._urls = urls;
}

ChannelAcl.prototype.init = function(opts){
    var urls = this._urls;
    $('#tt').propertygrid({
        height:'auto',
        url:urls.queryUrl,
        showGroup:true,
        scrollbarSize:0,
        singleSelect:true,
        loadMsg:'',
        columns:[[
            {field:'ck',checkbox:true},
            {field:'name',title:'名称',width:100},
            {field:'value',title:'权限',width:50},
            {field:'explain',title:'说明',width:400,
            	formatter : function(val, rec) {
            		if (rec.value=='1'){
            			return '<font color="blue">具有访问本栏目内信息列表的权利</font>，<font color="red">但不具有对栏目内信息的操作权利</font>。';
            		}else if (rec.value=='2'){
            			return '<font color="blue">具有对本栏目内信息进行新增、修改操作的权利</font>，<font color="red">但不具有删除、审核、发布的权利。</font>';
            		}else if (rec.value=='4'){
            			return '<font color="blue">具有对本栏目内信息进行删除操作的权利</font>，<font color="red">但不具有审核、发布的权利。</font>';
            		}else if (rec.value=='8'){
            			return '<font color="blue">具有对本栏目内信息进行审核操作的权利</font>，<font color="red">但不具有发布的权利。</font>';
            		}else if (rec.value=='16'){
            			return '<font color="blue">具有对本栏目内信息所有操作的权利</font>。';
            		}else if (rec.value=='32'){
            			return '<font color="blue">具有对本栏目内信息所有操作的权利并具有栏目创建的权利</font>。';
            		}else if (rec.value=='64'){
            			return '<font color="blue">具有对本栏目内信息所有操作的权利并具有栏目创建、修改的权利</font>。';
            		}else if (rec.value=='128'){
            			return '<font color="blue">具有对本栏目内信息所有操作的权利并具有栏目所有的权利</font>。';
            		}else if (rec.value=='256'){
            			return '<font color="blue">具有对本栏目进行管理的权利</font>。';
            		}else if (rec.value=='true'){
            			return '<font color="blue">继承了上级栏目所有权利</font>。';
            		}else if (rec.value=='false'){
            			return '<font color="red">不继承上级栏目所有权利</font>。';
            		}
            	}
            }
        ]],
        onBeforeLoad:function(param){
             param['id'] = opts.id;    
        },
        onSelect:function(rowIndex,data){
            if(data.group == '其他'){
                $('#btnremove').linkbutton('disable');
            }else{
                $('#btnremove').linkbutton('enable');
            }
        },
        onAfterEdit:function(rowIndex,data,changes){
          if(changes){
              var params = {};
              params['id'] = opts.id;
              if(data.name == '继承权限'){
            	  if(typeof(changes.value) == 'undefined') return;
                  params['inherit'] = changes.value;
                  $.post(urls.inheritUrl,params,function(data){
                      if(!data.success){
                          $.messager.alert('错误','继承权限错误','error');
                      }
                   });
              }else{
            	  if(typeof(changes.value) == 'undefined') return;
                  params['name'] = data.name;
                  params['mask'] = changes.value;
                  $.post(urls.saveUrl,params,function(data){
                      if(!data.success){
                          $.messager.alert('错误','更新权限错误','error');
                      }
                   });    
              }
          }
        },
        toolbar:[{
            id:'btnadd',
            text:'添加',
            iconCls:'icon-add',
            handler:function(){
                openWindow('#edit-window',{title:'增加 - 权限',width:400,height:200})
          }
        },'-',{
            id:'btnremove',
            text:'删除',
            iconCls:'icon-remove',
            handler:function(){
                var rows = $('#tt').datagrid('getSelections');
                if(rows.length == 0){
                    $.messager.alert('提示','请选择删除的记录','info');
                    return;
                }
                var parameter ='id=' + opts.id + '&name=' + rows[0].name;
                $.messager.confirm('提示', '确定删除所选记录?', function(r){
                    if (r){
                        $.post(urls.removeUrl,parameter,function(data){
                            if(data.success){
                                $("#tt").datagrid('reload');
                            }else{
                                $.messager.alert('错误','删除权限错误');
                            }
                      });
                    }
                },'info');
            }
        },'-',{
           	id:'btnok',
            text:'保存',
            iconCls:'icon-save',
            handler:function(){
            	$.messager.alert('提示','保存成功','info');
            }
        }]
    });    
    
    $('#button-save').bind('click',function(){
        var value = $("#permissionform").serialize();
        $.post(urls.saveUrl,value,function(data){
           if(data.success){
               $('#tt').propertygrid('reload');
           } else{
               $.messager.alert('错误',data.message,'error');
           }
        });
    });
        
    $('#query-tt').datagrid({
        width:500,
        pageSize:5,
        nowrap: false,
        rownumbers:true,
        idField:'',
        pagination:true,
        pageList:[5],
        singleSelect:true,
        loadMsg:'',
        frozenColumns:[[
             {field:'ck',checkbox:true}
        ]],
        columns:[[]]
    });
    
    $('#button-query').bind('click',function(){
        var type =  $('#cc').combobox('getValue');
        if(type == 'user'){
            $('#query-label-name').html('用户名:');
            $('#query-label-desc').html('姓名:')
            $('#query-input-name').attr('name','username');
            $('#query-input-desc').attr('name','name');
            var columns = [[{field:"username",title:'用户名',width:200},
                            {field:"userInfo",title:'姓名',width:230,formatter:function(value,row){
                                return value.name;}}]];
            var idField = "username";
        }else{
            $('#query-label-name').html('名称:');
            $('#query-label-desc').html('描述:')
            $('#query-input-name').attr('name','name');
            $('#query-input-desc').attr('name','remark');
            var columns = [[{field:"name",title:'名称',width:200},
                            {field:"remark",title:'描述',width:230}]];
            var idField = "name";
        }
        
        var url = urls[type + 'QueryUrl'];
        $('#query-tt').datagrid({
            idField:idField,
            columns:columns,
            url:url
         });
        
        var title = '权限';
        if(type == 'user'){
            title = "用户";
        }else if(type == 'group'){
            title = "用户组";
        }
        
        openWindow('#query-window',{title:title,width:523,height:290});        
    });
    
    $('#button-selected').bind("click",function(){
        var type =  $('#cc').combobox('getValue');
        var row = $('#query-tt').datagrid("getSelected");
        if(row.length == 0){
            $.messager.alert('提示','请选择记录','info');
            return;
        }
        var name = '';
        if(type == 'user'){
            name = row.username;
        }else{
            name = row.name;
        }
        
       $('#input-name').val(name);
       $('#query-window').window('close');
    });
}
     