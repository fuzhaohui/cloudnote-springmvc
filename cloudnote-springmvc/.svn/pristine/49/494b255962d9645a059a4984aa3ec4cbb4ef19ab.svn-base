function ajaxFileUpload()
    {
        $("#loading")
        .ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
        });//文件上传完成将图片隐藏起来
        
        $.ajaxFileUpload
        (
            {
                url:'note/uploadAttachment.do',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'attachment',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
				data:{
					noteId:$("#spanNoteId").html(),
					randomQuery:(new Date()).getTime(),
					isIE:$.browser.msie
				},
                success: function (data, status)  //服务器成功响应处理函数
                {	
					var pos = data.fileName.lastIndexOf(".");
					var lastname = data.fileName.substring(pos,data.fileName.length);
					$("#divUrlHidden").html(data.img);
					$("#divTypeHidden").html(lastname);
					$("#divFileNameHidden").html(data.fileName);
					$("#loading").hide();
					$("#divUpload").hide();
					$(".insertImage").click();
                    //alert(eval("data = " + data ));//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert("文件必须小于10M");
                }
            }
        )
        
        return false;

    }

$(document).ready(function () {
    initSize();
    initDom();
	
});
var a = 0;
var b = 0;
$(window).resize(function () {

    initSize();

    $("#divNoteEditor .jHtmlArea").width($("#divNoteEditor").width());
    $("#divNoteEditor .jHtmlArea .ToolBar").width($("#divNoteEditor").width());

    $("#divNoteEditor .jHtmlArea .ToolBar").next().children(".iframe-jHtmlArea").height($("#divNoteEditor").height() - 24);
    $("#divNoteEditor .jHtmlArea .ToolBar").next().children(".iframe-jHtmlArea").width($("#divNoteEditor").width());


});

var initSize = function () {
	$("#divUpload").css("top",$(document.body)[0].clientHeight/2-150);
	$("#divUpload").css("left",$(document.body)[0].clientWidth/2+50);
    $("#divLeft").height($(document.body)[0].clientHeight - 50);
    $("#divRight").height($(document.body)[0].clientHeight - 50);
    $("#divRight").width($(document.body)[0].clientWidth - 300);
    $("#divNoteInfo").width($("#divRight").width());
    $("#divNoteEditor").height($("#divRight").height() - 40);
    $("#divNoteEditor").width($("#divRight").width());
	$("#divLoading").css("top",$("#divLeft").height()/2);
}

var initDom = function () {

    $("body").click(function (event) {
        if ("fontFamily" != $(event.target).attr("class") && "li-font" != $(event.target).attr("class") && "changeFontFamily" != $(event.target).attr("class")) {
            $("#divNoteEditorFontFamily").fadeOut(300);
        }
        if ("fontSize" != $(event.target).attr("class") && "li-font" != $(event.target).attr("class") && "changeFontSize" != $(event.target).attr("class")) {
            $("#divNoteEditorFontSize").fadeOut(300);
        }
		if ("li-version" != $(event.target).attr("class")&&" span-buttons-version-list"!= $(event.target).attr("class")) {
            $("#divVersionList").fadeOut(150);
			$("#divPreview").fadeOut(150);
        }
    });
	
	$("#divCloseUpload").click(function(event){
		$("#divUpload").hide();
	});

    if ("搜索笔记" == $("#inputTextSearch").val()) {
        $("#inputTextSearch").css("color", "#ccc");
    } else {
        $("#inputTextSearch").css("color", "#777");
    }

    $("#inputTextSearch").focus(function (event) {
        if ("搜索笔记" == $(event.target).val()) {
            $(event.target).val("");
            $(event.target).css("color", "#777");
        }
    });
 
    $("#inputTextSearch").blur(function (event) {
        if ("" == $(event.target).val()) {
            $(event.target).val("搜索笔记");
            $(event.target).css("color", "#ccc");
        }
    });
	
	$("#inputTextSearch").bind('keydown', function (e) {
        var key = e.which;
        if (key == 13) {
			$("#inputTextSearch").val($("#inputTextSearch").val().replace("<br>",""));
			$("#inputTextSearch").blur();
            $("#divSearchButton").click();
			return false;
		}
    });
	
	$("#inputTextSearch").focus(function (event) {
            $(event.target).animate({
                borderColor: "rgb(221,75,57)"
            }, {
                duration: 300,
                queue: false
            });
            $(event.target).next().animate({
                backgroundColor: "rgb(221,75,57)",
                color: "#777"
            }, {
                duration: 300,
                queue: false
            });
        });
        $("#inputTextSearch").blur(function (event) {
            $(event.target).animate({
                borderColor: "#777"
            }, {
                duration: 300,
                queue: false
            });
            $(event.target).next().animate({
                backgroundColor: "#777",
                color: "white"
            }, {
                duration: 300,
                queue: false
            });
        });

    createNotebookListTree("notebook/listUserNoteBook.do");

    $("#divSearchButton").click(function (event) {
		if("搜索笔记"!=$("#inputTextSearch").val()){
			createNotebookListTree("notebook/searchNotes.do");
		}else{
			createNotebookListTree("notebook/listUserNoteBook.do");
		}
    });
	
	$("#spanNoteName").click(function(event){
		this.contentEditable=true;
		$(this).focus();
		$(this).css("outline","rgb(221,75,57) auto 5px");
	});
	
	$("#spanNoteName").blur(function(event){
		this.contentEditable=false;
		$(this).css("outline","none");
		$.get("note/editNote.do", {
			"note.notes_title":$("#spanNoteName").html(),
			"note.notes_id":$("#spanNoteId").html(),
			"note.version":$("#spanNoteVersion").html(),
			randomQuery:(new Date()).getTime()
		},function(){
			createNotebookListTree("notebook/listUserNoteBook.do");
		});
	});
	
	$("#spanNoteName").bind('keydown', function (e) {
		var key = e.which;
		if (key == 13) {
			$("#spanNoteName").blur();
			return false;
		}
	});


}

var createNotebookListTree = function (url) {
	$("#divLoading").show();
    $.get(url,
	{"searchParam":$("#inputTextSearch").val(),
					randomQuery:(new Date()).getTime()}, 
	function (data) {
		var noteListTreeHtml = "";
		if("notebook/listUserNoteBook.do"==url){
			noteListTreeHtml = noteListTreeHtml+"<div class='div-add-notebook' id='divAddNotebook'>&nbsp;+添加笔记本</div>";
		}else{
			noteListTreeHtml = noteListTreeHtml+"<div class='div-search-result'>搜索结果</div><div class='div-search-result-clear' id='divSearchResultClear' title='清空搜索结果'>✘</div>";
		}
        var tempNoteList = data.noteBookList;
        var notebookCount = tempNoteList.length;
		if(null!=data.lastEditNote){
			var notebookDefaultDisplayId = data.lastEditNote.notebook_id;
			var notesDefaultDisplayId = data.lastEditNote.notes_id;
			var notesDefaultDisplayVersion = data.lastEditNote.version;
		}
        for (var i = 0; i < notebookCount; i++) {

            var notesCount = tempNoteList[i].notes.length;
            noteListTreeHtml = noteListTreeHtml + "<div class='div-notebook' id='divNotebook" + tempNoteList[i].notebook_id + "'>" + tempNoteList[i].notebook_name + "<span class='span-notes-count'>(" + notesCount + ")</span>" + "</div><ul class='ul-note-list'>";
			if(-1!=tempNoteList[i].notebook_id){
				noteListTreeHtml = noteListTreeHtml + "<li class='li-notebook-buttons'><span class='span-notebook-buttons span-notebook-buttons-rename' title='重命名笔记本'>✎</span><span class='span-notebook-buttons span-notebook-buttons-delete' title='删除笔记本'>✘</span><span title='添加笔记'  class='span-notebook-buttons span-add-note'>+</span></li>";
			}else{
				noteListTreeHtml = noteListTreeHtml + "<li class='li-notebook-buttons'><span class='span-notebook-buttons span-notebook-buttons-remove-all' title='清空所有笔记'>✘</span></li>";
			}
			if(-1!=tempNoteList[i].notebook_id){
				for (var j = 0; j < notesCount; j++) {
					noteListTreeHtml = noteListTreeHtml + "<li class='li-note' id='liNote_" + tempNoteList[i].notes[j].notes_id + "_" + tempNoteList[i].notes[j].version + "'>" + tempNoteList[i].notes[j].notes_title + "<div class='div-note-update-time'><img src='./resource/images/update_time.png'> " + tempNoteList[i].notes[j].last_modified.replace("T", " ") + "<div class='div-note-buttons'><span title='查看历史版本' class='span-buttons span-buttons-version-list'>▤</span><span title='删除笔记' class='span-buttons span-buttons-delete-note'>✘</span></div></div></li>";
				}
			}else{
				for (var j = 0; j < notesCount; j++) {
					noteListTreeHtml = noteListTreeHtml + "<li class='li-note-delete' id='liNote_" + tempNoteList[i].notes[j].notes_id + "_" + tempNoteList[i].notes[j].version + "'>" + tempNoteList[i].notes[j].notes_title + "<div class='div-note-update-time'><img src='./resource/images/update_time.png'> " + tempNoteList[i].notes[j].last_modified.replace("T", " ") + "<div class='div-note-buttons'><span title='恢复' class='span-buttons span-buttons-restore'>↖</span><span title='永久删除' class='span-buttons span-buttons-remove'>✘</span></div></div></li>";
				}
			}
            noteListTreeHtml = noteListTreeHtml + "</ul>";

        };

        $("#divLeft").html(noteListTreeHtml);
		if(null!=data.lastEditNote){
			$("#divNoteEditor").html("<textarea id='textareaJHtmlArea' class='textarea-jHtmlArea'></textarea>");


		    $("#textareaJHtmlArea").html(data.lastEditNote.content);
			loadJHtmlArea();

		    $("#divNoteEditor .jHtmlArea .ToolBar").next().children(".iframe-jHtmlArea").height($("#divNoteEditor").height() - 24);
		    $("#divNoteEditor .jHtmlArea .ToolBar").next().children(".iframe-jHtmlArea").width($("#divNoteEditor").width());
			
		    $("#liNote_" + notesDefaultDisplayId + "_" + notesDefaultDisplayVersion).css("color", "rgb(221,75,57)");
			//if("notebook/listUserNoteBook.do"==url){
				$("#divNotebook" + notebookDefaultDisplayId).css("border-color", "rgb(221,75,57)").next().show();
			//}else{
			//	$("#divNotebook0").css("border-color", "rgb(221,75,57)").next().show();
			//}

			$("#spanNoteName").html($("#liNote_" + notesDefaultDisplayId + "_" + notesDefaultDisplayVersion).html().toLowerCase().split("<div")[0]);

			$("#spanNoteId").html(notesDefaultDisplayId);
			$("#spanNoteVersion").html(notesDefaultDisplayVersion);
			$("#spanNotebookId").html(notebookDefaultDisplayId);
		}else{
			$("#divNoteEditor").html("<textarea id='textareaJHtmlArea' class='textarea-jHtmlArea'></textarea>");
		    $("#textareaJHtmlArea").html("");
			$("#spanNoteName").html("");
			loadJHtmlArea();
		}
        $("#divLeft .div-notebook").click(function (event) {
            if ("div-notebook" == $(event.target).attr("class")) {
                $(event.target).next().toggle(300);
            } else {
                $(event.target).parent().next().toggle(300);
            }
        });

        $("#divLeft .div-notebook").mouseover(function (event) {
            if ("div-notebook" == $(event.target).attr("class")) {
                $(event.target).animate({
                    color: "rgb(221,75,57)"
                }, {
                    duration: 300,
                    queue: false
                });
            } else {
                $(event.target).parent().animate({
                    color: "rgb(221,75,57)"
                }, {
                    duration: 300,
                    queue: false
                });
            }
        });
        $("#divLeft .div-notebook").mouseout(function (event) {
            if ("div-notebook" == $(event.target).attr("class")){
                $(event.target).animate({
                    color: "#777"
                }, {
                    duration: 300,
                    queue: false
                });
            } else {
                $(event.target).parent().animate({
                    color: "#777"
                }, {
                    duration: 300,
                    queue: false
                });
            }
        });
		
        $("#divSearchResultClear").click(function(event){
			createNotebookListTree("notebook/listUserNoteBook.do");
		});
		
		$("#divSearchResultClear").mouseover(function(event){
			$(event.target).animate({
                    color: "rgb(221,75,57)"
                }, {
                    duration: 300,
                    queue: false
                });
		});
		
		$("#divSearchResultClear").mouseout(function(event){
			$(event.target).animate({
                    color: "#777"
                }, {
                    duration: 300,
                    queue: false
                });
		});
			
		$("#divLeft ul li .span-notebook-buttons").mouseover(function(event){
			$(event.target).animate({
                color: "rgb(221,75,57)",
				borderColor: "rgb(221,75,57)"
            }, {
                duration: 150,
                queue: false
            });
		});
		
		$("#divLeft ul li .span-notebook-buttons").mouseout(function(event){
			$(event.target).animate({
                color: "#aaa",
				borderColor: "#aaa"
            }, {
                duration: 150,
                queue: false
            });
		});
		
		$("#divLeft ul li div div .span-buttons").mouseover(function(event){
			$(event.target).animate({
                color: "rgb(221,75,57)",
				borderColor: "rgb(221,75,57)"
            }, {
                duration: 150,
                queue: false
            });
		});
		
		$("#divLeft ul li div div .span-buttons").mouseout(function(event){
			$(event.target).animate({
                color: "#aaa",
				borderColor: "#aaa"
            }, {
                duration: 150,
                queue: false
            });
		});
		
		$("#divLeft ul li .span-add-note").click(function(event){
			event.target.contentEditable = true;
			$(event.target).html("");
			$(event.target).animate({
                width:"100px"
            }, {
                duration: 150,
                queue: false,
				complete:function(){
					$(event.target).focus();
				}
            });
			//
		});
		
		$("#divAddNotebook").click(function(event){
			event.target.contentEditable = true;
			$(event.target).html("");
			$(event.target).animate({
                width:"200px"
            }, {
                duration: 150,
                queue: false,
				complete:function(){
					$("#divAddNotebook").focus();
				}
            });
		});
		
		$("#divAddNotebook").bind('keydown', function (event) {
			var key = event.which;
			if (key == 13) {
			 
				$("#divAddNotebook").html($("#divAddNotebook").html().replace("<br>",""));
				$("#divAddNotebook").unbind("blur");
				if(""!=$("#divAddNotebook").html()){
					//console.log("enter:"+(new Date).getTime());
					$.ajax({
						type: "GET",
						url: "notebook/addNoteBook.do",
						dataType: "json",
						data:{
							"noteBook.notebook_name": $("#divAddNotebook").html(),
							randomQuery:(new Date()).getTime()
						},
						success: function(data){
							createNotebookListTree("notebook/listUserNoteBook.do");
							$("#divAddNotebook").bind("blur");
						}
					});
				}else{
					//console.log("enter:"+(new Date).getTime());
					createNotebookListTree("notebook/listUserNoteBook.do");
					//alert("输入的笔记本名不能为空");
				};
				return false;
			}
		});
		
		
		$("#divLeft ul li .span-notebook-buttons-rename").click(function(event){
			$(event.target).parent().parent().prev()[0].contentEditable=true;
			$(event.target).parent().parent().prev().children().remove();
			$(event.target).parent().parent().prev().focus();
			$(event.target).parent().parent().prev().unbind('click');
			$(event.target).parent().parent().prev().unbind('mouseover');
			$(event.target).parent().parent().prev().unbind('mouseout');
		});
		
		$("#divLeft .div-notebook").bind('keydown', function (e) {
			var key = e.which;
			if (key == 13) {
				$("#divLeft .div-notebook").blur();
				return false;
			}
		});
		
		$("#divLeft .div-notebook").blur(function(event){
			$(event.target).html($(event.target).html().replace("<br>",""));
			if(""!=$(event.target).html()){
				if("true"==event.target.contentEditable){
					event.target.contentEditable=false;
					$.get("notebook/renameNoteBook.do", {
								"noteBookId": $(event.target).attr("id").split("divNotebook")[1],
								"newNoteBookName":$(event.target).html(),
					randomQuery:(new Date()).getTime()
					},function(){
						createNotebookListTree("notebook/listUserNoteBook.do");
						$(event.target).unbind('click');
						$(event.target).unbind('mouseover');
						$(event.target).unbind('mouseout');
					});
				}
			}else{
				createNotebookListTree("notebook/listUserNoteBook.do");
				//alert("输入的笔记本名不能为空");
			}
		});
		
		$("#divLeft ul li .span-add-note").blur(function(event){
				$(event.target).html($(event.target).html().replace("<br>",""));
				console.log("blur:"+(new Date).getTime());
				if(""!=$(event.target).html()){
				
					$.get("note/addNote.do", {
							"note.notes_title": $(event.target).html(),
							"note.content":"",
							"note.notebook_id":$(event.target).parent().parent().prev().attr("id").split("divNotebook")[1],
							randomQuery:(new Date()).getTime()
						},function(){
							createNotebookListTree("notebook/listUserNoteBook.do");
						});
				}else{
					createNotebookListTree("notebook/listUserNoteBook.do");
					//alert("输入的笔记名不能为空");
				}
		});
		
		$("#divLeft ul li .span-add-note").bind('keydown', function (event) {
			var key = event.which;
			if (key == 13) {
				$("#divLeft").focus();
				$("#divLeft ul li .span-add-note").unbind("blur");
				//console.log("enter:"+(new Date).getTime());
				$(event.target).html($(event.target).html().replace("<br>",""));
				if(""!=$(event.target).html()){
				
					$.get("note/addNote.do", {
							"note.notes_title": $(event.target).html(),
							"note.content":"",
							"note.notebook_id":$(event.target).parent().parent().prev().attr("id").split("divNotebook")[1],
							randomQuery:(new Date()).getTime()
						},function(){
							createNotebookListTree("notebook/listUserNoteBook.do");
						});
				}else{
					createNotebookListTree("notebook/listUserNoteBook.do");
					//alert("输入的笔记名不能为空");
				}
				return false;
			}
		});
		
		$("#divAddNotebook").blur(function(event){
			$("#divAddNotebook").html($("#divAddNotebook").html().replace("<br>",""));
			if(""!=$("#divAddNotebook").html()){
				//console.log("blur:"+(new Date).getTime());
				$.ajax({
					type: "GET",
					url: "notebook/addNoteBook.do",
					dataType: "json",
					data:{
						"noteBook.notebook_name": $("#divAddNotebook").html(),
					randomQuery:(new Date()).getTime()
					},
					success: function(data){
						createNotebookListTree("notebook/listUserNoteBook.do");
					}
				});
				//$.get("notebook/addNoteBook.do", {
				//	"noteBook.notebook_name": $("#divAddNotebook").html()
				//},function(){
				//	createNotebookListTree();
				//});
			}else{
				//console.log("blur:"+(new Date).getTime());
				createNotebookListTree("notebook/listUserNoteBook.do");
				//alert("输入的笔记本名不能为空");
			};
		});

        $("#divLeft ul .span-buttons-delete-note").click(function (event) {
            if (confirm('确定要删除此笔记?')) {
                $.get("note/deleteNote.do", {
                    noteId: $(event.target).parent().parent().parent().attr("id").split("_")[1],
					randomQuery:(new Date()).getTime()
                },function(data){
					createNotebookListTree("notebook/listUserNoteBook.do");
				});
            }else{
				return false;
			}
        });
		
		$("#divLeft ul .span-buttons-version-list").click(function (event) {
			var tempHtml="<ul>";
			$("#divVersion").html("");
			$.get("note/detailNote.do", {
                noteId: $(event.target).parent().parent().parent().attr("id").split("_")[1],
				version: $(event.target).parent().parent().parent().attr("id").split("_")[2],
				randomQuery:(new Date()).getTime()
            },function(data){
				$("#divVersionNoteId").html($(event.target).parent().parent().parent().attr("id").split("_")[1]);
				var versionList=data.noteVersions;
				var versionListCount = versionList.length;
				for(var i = 0; i < versionListCount; i++){
					tempHtml=tempHtml+"<li class='li-version'><span class='span-version-index'>version:<span class='span-version'>"+versionList[i].version+"</span>　<img src='./resource/images/update_time.png'>"+versionList[i].last_modified.replace("T"," ")+"</span></li>";
				}
				tempHtml=tempHtml+"</ul>";
				
				$("#divVersionList").html(tempHtml);
				
				if($(event.target).offset().top>$(document.body)[0].clientHeight-$("#divVersionList").height()){
					$("#divVersionList").css("top",$(document.body)[0].clientHeight-$("#divVersionList").height()-5);
				}else{
					$("#divVersionList").css("top",$(event.target).offset().top);
				}
				$("#divVersionList").css("left",$(event.target).offset().left+25);
				
				$("#divVersionList ul .li-version").click(function (e) {
					if (confirm('要恢复笔记到选择的版本?')) {
						$.get("note/restNoteVersion.do", {
							noteId: $("#divVersionNoteId").html(),
							version: $(this).children().children(".span-version").html(),
							randomQuery:(new Date()).getTime()
						},function(data){
							createNotebookListTree("notebook/listUserNoteBook.do");
						});
						$("#divVersionList").hide(150);
						$("#divPreview").hide(150);
					}else{
						return false;
					}
					
				});
				
				$("#divVersionList ul .li-version").mouseover(function (e) {
					$(this).css("background-color","rgb(221,75,57)");
					$(this).children().css("color","white");
					
					if($("#divVersion").html()!=$(this).children().children(".span-version").html()){
						$("#divVersion").html($(this).children().children(".span-version").html());
						$("#divPreview").css("left",$(this).offset().left+250);
						$.get("note/detailNote.do", {
							noteId: $("#divVersionNoteId").html(),
							version: $(this).children().children(".span-version").html(),
							randomQuery:(new Date()).getTime()
						},function(contentData){
							if(0<contentData.note.content.indexOf("<img")){
								$("#divPreview").html("<div>历史版本预览:<div><hr>"+contentData.note.content.replace(/<img/g,"<img style='max-height:50px;'"));
								
								$("#divLoading").show();
								$("#divPreview img").load(function(){
									$("#divLoading").hide();
									if($(e.target).offset().top>($(document.body)[0].clientHeight-$("#divPreview").height()-15)){
										$("#divPreview").css("top",$(document.body)[0].clientHeight-$("#divPreview").height()-15);
									}else{
										$("#divPreview").css("top",$(e.target).offset().top);
									}
								});
								$("#divPreview img").error(function(){
									$("#divLoading").hide();
									/*$(this).attr("src","./resource/images/load_error.png");*/
								});
							}else{
								$("#divPreview").html("<div>历史版本预览:<div><hr>"+contentData.note.content);
								if($(e.target).offset().top>($(document.body)[0].clientHeight-$("#divPreview").height()-15)){
									$("#divPreview").css("top",$(document.body)[0].clientHeight-$("#divPreview").height()-15);
								}else{
									$("#divPreview").css("top",$(e.target).offset().top);
								}
							}
							
							
							$("#divPreview").fadeIn();
						});
					}
					
				});
				$("#divVersionList ul .li-version").mouseout(function (e) {
					$(this).css("background-color","white");
					$(this).children().css("color","#777");
					if($("#divVersion").html()!=$(this).children().children(".span-version").html()){
						$("#divVersion").html($(this).children().children(".span-version").html());
						$("#divPreview").hide();
					}
				});
				$("#divVersionList ul .li-version").click(function(e){
					
				});
				$("#divVersionList").show(150);
				$("#divPreview").hide();
			});
        });
		
		$("#divLeft ul .span-buttons-remove").click(function (event) {
            if (confirm('确定要永久删除此笔记?')) {
                $.get("note/removeNote.do", {
                    noteId: $(event.target).parent().parent().parent().attr("id").split("_")[1],
					randomQuery:(new Date()).getTime()
                },function(data){
					createNotebookListTree("notebook/listUserNoteBook.do");
				});
            }else{
				return false;
			}
        });
		
		 $("#divLeft ul .span-notebook-buttons-remove-all").click(function (event) {
            if (confirm('确定要永久删除笔记?')) {
                $.get(" note/removeAllNotes.do", {
                    userId: 1,
					randomQuery:(new Date()).getTime()
                },function(data){
					createNotebookListTree("notebook/listUserNoteBook.do");
				});
            }else{
				return false;
			}
        });
		
		$("#divLeft ul .span-buttons-restore").click(function (event) {
            if (confirm('确定要恢复此笔记?')) {
                $.get("note/restNote.do", {
                    noteId: $(event.target).parent().parent().parent().attr("id").split("_")[1],
					randomQuery:(new Date()).getTime()
                },function(data){
					createNotebookListTree("notebook/listUserNoteBook.do");
				});
            }else{
				return false;
			}
        });
		
		$("#divLeft ul li .span-notebook-buttons-delete").click(function (event) {
            if (confirm('确定要删除此笔记本?')) {
                $.get("notebook/removeNoteBook.do", {
                    noteBookId: $(event.target).parent().parent().prev().attr("id").split("divNotebook")[1],
					randomQuery:(new Date()).getTime()
                },function(data){
					createNotebookListTree("notebook/listUserNoteBook.do");
				});
            }else{
				return false;
			}
        });

        $("#divLeft ul .li-note").click(function (event) {
			if($(event.target).attr("class")=="li-note"){

				$.get("note/detailNote.do", {
					noteId: $(event.target).attr("id").split("_")[1],
					version: $(event.target).attr("id").split("_")[2],
					randomQuery:(new Date()).getTime()
				},

				function (detailNote) {

					$("#spanNoteName").hide(150, function () {
						$("#spanNoteName").html($(event.target).html().toLowerCase().split("<div")[0]);
						$("#spanNoteId").html($(event.target).attr("id").split("_")[1]);
						$("#spanNoteVersion").html($(event.target).attr("id").split("_")[2]);
						$("#spanNotebookId").html($(event.target).parent().prev().attr("id").split("divNotebook")[1]);
						$("#spanNoteName").show(150);
						$("#spanNoteName").animate({
							color: "rgb(221,75,57)"
						}, {
							duration: 500,
							queue: false,
							complete: function () {
								$("#spanNoteName").animate({
									color: "#777"
								}, {
									duration: 500,
									queue: false
								})
							}
						});
					});

					$("#divLeft ul .li-note").animate({
						color: "#777"
					}, {
						duration: 300,
						queue: false
					});

					$("#divLeft .div-notebook").animate({
						borderColor: "#f3f3f3"
					}, {
						duration: 300,
						queue: false
					});

					$(event.target).animate({
						color: "rgb(221,75,57)"
					}, {
						duration: 100,
						queue: true
					}).animate({
						color: "#777"
					}, {
						duration: 100,
						queue: true
					}).animate({
						color: "rgb(221,75,57)"
					}, {
						duration: 100,
						queue: true
					});

					$(event.target).parent().prev().animate({
						borderColor: "rgb(221,75,57)"
					}, {
						duration: 300,
						queue: false
					});

					$("#divNoteEditor").html("<textarea id='textareaJHtmlArea' class='textarea-jHtmlArea'></textarea>");

					$("#textareaJHtmlArea").html(detailNote.note.content);

					loadJHtmlArea();
					$("#divNoteEditor .jHtmlArea").width($("#divNoteEditor").width());
					$("#divNoteEditor .jHtmlArea .ToolBar").width($("#divNoteEditor").width());

					$("#divNoteEditor .jHtmlArea .ToolBar").next().children(".iframe-jHtmlArea").height($("#divNoteEditor").height() - 24);
					$("#divNoteEditor .jHtmlArea .ToolBar").next().children(".iframe-jHtmlArea").width($("#divNoteEditor").width());

				});
			}
        });

        $(".li-font").mouseover(function (event) {
            $(event.target).css("background-color", "rgb(221,75,57)");
            $(event.target).css("color", "white");
        });
        $(".li-font").mouseout(function (event) {
            $(event.target).css("background-color", "white");
            $(event.target).css("color", "#777");
        });
        $("#divNoteEditorFontFamily ul .li-font").mouseover(function (event) {
            var tempHtmlFontFamily = $(event.target).html();
            $("#liSelectFontFamilyHidden").html(tempHtmlFontFamily);
            $(".changeFontFamily").click();
        });
        $("#divNoteEditorFontFamily ul .li-font").click(function (event) {
            $("#divNoteEditorFontFamily").hide();
        });
        $("#divNoteEditorFontSize ul .li-font").mouseover(function (event) {
            var tempHtmlFontSize = $(event.target).html();
            $("#liSelectFontSizeHidden").html(tempHtmlFontSize);
            $(".changeFontSize").click();
        });
        $("#divNoteEditorFontSize ul .li-font").click(function (event) {
            $("#divNoteEditorFontSize").hide();
        });

		$("#divLoading").hide();
    });
}

var loadJHtmlArea = function () {
    $("#textareaJHtmlArea").htmlarea({
        toolbar: [
            [{
                css: "edit",
                text: "编辑",
                action: function (btn) {
				
					//$.get("note/detailNote.do", {
					//		noteId: $("#spanNoteId").html(),
					//		version: $("#spanNoteVersion").html()
					//	},function (detailNote) {
							
							

							//$("#textareaJHtmlArea").html(detailNote.note.content);

							var edit = this.editor = this.iframe[0].contentWindow.document;
							//edit.updateHtmlArea();
							edit.body.contentEditable = true;
							$(edit.body).animate({
								borderColor: "rgb(221,75,57)"
							}, {
								duration: 500,
								queue: false
							});
							$(edit.body).css("background-color", "white");
							$(edit.body).focus();
							$($(".ToolBar").children()[0]).hide();
							$($(".ToolBar").children()[0]).next().show(300);
							//$(this.toolbar).children().children().children().css("display","block");
							//$(this.toolbar).children().children().children(".edit").css("display","none");
							//$(this.toolbar).children().children().children(".edit").parent().css("display","none");
					//	});
                }
            }],
            [{
                css: "save",
                text: "保存",
                action: function (btn) {
					
                    var edit = this.editor = this.iframe[0].contentWindow.document;
					var aList= $("a", document.getElementById("nameJHtmlArea").contentWindow.document);
					for(var i = 0; i<aList.length; i++){
						$(aList[i]).attr("target","_blank");
					}
                    edit.body.contentEditable = false;
                    $(edit.body).animate({
                        borderColor: "#f3f3f3"
                    }, {
                        duration: 500,
                        queue: false
                    });
					
					//$("img", document.getElementById("nameJHtmlArea").contentWindow.document).css("cursor","pointer").html("");  
                    $(edit.body).css("background", "url(./resource/images/noise.png) #f3f3f3");
                    $($(".ToolBar").children()[0]).show(300);
                    $($(".ToolBar").children()[0]).next().hide(300);
					
                    $.get("note/addNote.do", {
                        "note.notes_id": $("#spanNoteId").html(),
						"note.notes_title": $("#spanNoteName").html(),
						"note.content":this.editor.body.innerHTML,
						"note.notebook_id":$("#spanNotebookId").html(),
					randomQuery:(new Date()).getTime()
                    },function(data){
						createNotebookListTree("notebook/listUserNoteBook.do");
					});
                }
            },
                "bold",
                "italic",
                "underline",
                "strikethrough",
                "|", {
                css: "fontFamily",
                text: "字体",
                action: function (btn) {
                    //console.dir($('#textareaJHtmlArea').htmlarea('getRange'));
                    $("#divNoteEditorFontFamily").fadeIn(300);
                }
            }, {
                css: "fontSize",
                text: "字体大小",
                action: function (btn) {
                    //console.dir($('#textareaJHtmlArea').htmlarea('getRange'));
                    $("#divNoteEditorFontSize").fadeIn(300);
                }
            },
                "forecolor",
                "|",
                "justifyLeft",
                "justifyCenter",
                "justifyRight",
                "indent",
                "outdent",
                "|",
                "orderedlist",
                "unorderedlist",
                "|",
                "link",
                "unlink",
                "|",
                "horizontalrule",
                {
					css: "upload",
					text: "",
					action: function (btn) {
                    $("#divUpload").show();
                }
				},
                "|",
                "html"],
            [{
                css: "changeFontFamily",
                text: "",
                action: function (btn) {
                    var edit = this.editor = this.iframe[0].contentWindow.document;
                    this.ec("fontName", false, $("#liSelectFontFamilyHidden").html());
                }
            }, {
                css: "changeFontSize",
                text: "",
                action: function (btn) {
                    var edit = this.editor = this.iframe[0].contentWindow.document;
                    this.ec("fontSize", false, $("#liSelectFontSizeHidden").html().split("(")[0]);
                }
            }, {
                css: "insertImage",
                text: "",
                action: function (btn) {
					url = $("#divUrlHidden").html();
                    this.ec("insertImage", false,url);
					
					var imgList= $("img", document.getElementById("nameJHtmlArea").contentWindow.document);
					for(var i = 0; i<imgList.length; i++){
						
						if(url==$(imgList[i]).attr("src")){
							//$("<a/>").attr("href",url.replace(".png",$("#divTypeHidden").html())).attr("target","_blank").insertBefore($(imgList[i]).css("border","none")).append($(imgList[i]));
							
							if("gif"==$("#divFileNameHidden").html().split(".")[($("#divFileNameHidden").html().split(".").length-1)].toLowerCase()||"png"==$("#divFileNameHidden").html().split(".")[($("#divFileNameHidden").html().split(".").length-1)].toLowerCase()||"jpg"==$("#divFileNameHidden").html().split(".")[($("#divFileNameHidden").html().split(".").length-1)].toLowerCase()){
							
							}else{
							
								$("<a/>").attr("href","attachment/download.do?fileName="+encodeURIComponent($("#divFileNameHidden").html())+"&noteId="+$("#spanNoteId").html()).attr("target","_blank").insertBefore($(imgList[i]).css("border","none")).append($(imgList[i]));
							}
						}
						//$(imgList[i]).css("cursor")="pointer";
						//$(imgList[i]).attr("data-path",$(imgList[i]).attr("src"));
					}
                }
            }]
        ]
    });
    $($(".ToolBar").children()[0]).show();
    $("body", window.frames['nameJHtmlArea'].document).click(function () {
        $("#divNoteEditorFontFamily").fadeOut(300);
    });
}