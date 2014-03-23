// JavaScript Document


var dialog = {
    init: function () {

        if (typeof document.body.style.maxHeight == "undefined") {//if IE 6
            $("body", "html").css({ height: "100%", width: "100%" });
            //$("html").css("overflow", "hidden");
            if (document.getElementById("TB_HideSelect") == null) {//iframe to hide select elements in ie6
                $("body").append("<iframe id='TB_HideSelect'></iframe><div id='TB_overlay'></div>");
            }
        } else {
            if (document.getElementById("TB_overlay") == null) {
                $("body").append("<div id='TB_overlay'></div>");
            }
        }

        var sbox = '<div class="popout" id="FB_dialog" title="点击上边框拖动" style="display:none"><table border="0" cellspacing="0" cellpadding="0"><tbody><tr><td class="bgcorner1"></td><td class="pobg1"></td><td class="bgcorner2"></td></tr><tr><td class="pobg4"></td><td><div class="popoutContent" id="FB_dialog_content"><span id="FB_dialog_loading" > 正在加载数据...</span></div></td><td class="pobg2"></td></tr><tr><td class="bgcorner4"></td><td class="pobg3"></td><td class="bgcorner3"></td></tr></tbody></table></div>'
        	$("body").append(sbox);
        //$("#TB_overlay").html(sbox);
        this.overlaySize();
        $(window).resize(function() {
        //	dialog.position();
        	});
       //$( "#FB_dialog" ).draggable();
        return this;
    },
    overlaySize: function () {
        if (window.innerHeight && window.scrollMaxY) {
            yScroll = window.innerHeight + window.scrollMaxY;
        } else if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
            yScroll = document.body.scrollHeight;
        } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
            yScroll = document.body.offsetHeight;
        }
        $("#TB_overlay").css("height", yScroll + "px");
        return this;
    },
    getPageSize: function () {
        var de = document.documentElement;
        var w = window.innerWidth || self.innerWidth || (de && de.clientWidth) || document.body.clientWidth;
        var h = window.innerHeight || self.innerHeight || (de && de.clientHeight) || document.body.clientHeight;

        arrayPageSize = new Array(w, h)
        return arrayPageSize;
    },
    getPageScrollTop: function () {
        var yScrolltop;
        if (self.pageYOffset) {
            yScrolltop = self.pageYOffset;
        } else if (document.documentElement && document.documentElement.scrollTop) {	 // Explorer 6 Strict
            yScrolltop = document.documentElement.scrollTop;
        } else if (document.body) {// all other Explorers
            yScrolltop = document.body.scrollTop;
        }
        arrayPageScroll = new Array('', yScrolltop)
        return arrayPageScroll;
    },
    position: function () {
        var pagesize = this.getPageSize();
        var arrayPageScroll = this.getPageScrollTop();
        var h = $("#FB_dialog").height();
        var w = $("#FB_dialog").width();
        $("#FB_dialog")
	.css({ left: ((pagesize[0] - w) / 2) + "px", top: (arrayPageScroll[1] + ((pagesize[1] - h) / 2)) + "px" })
	.css({ display: "block" })
    .css({ position: "absolute" });
        return this;
    },
    title: function (s) {
        if (s != undefined) {
            $("#FB_dialog_title").text(s);
            return this;
        }
        else {
            return $("#FB_dialog_title").text();
        }
        return this;
    },
    resize: function (w, h) {
        var _height = h ? h : 300;
        var _width = w ? w : 400;
        
        if (h != "auto" && w != "auto") {
            $("#FB_dialog_content")
        .css({ width: _width + "px", height: _height + "px" });
        }
        return this;
    },
    close: function () {
        $("#FB_dialog").fadeOut("fast", function () {
			$("#FB_dialog_iframe").unbind().remove();
            $('#TB_overlay,#FB_dialog,#TB_HideSelect').unbind().remove();
            if (navigator.userAgent.indexOf("MSIE") > 0) { //如果是IE 
                CollectGarbage();
            }
        });
        return this;
    },
    openUrl: function (u, w, h) {
        //{url,title,width,height}
        this.init();
        var d = (new Date).getTime();
        if (u.indexOf("?") == -1) {
            u = u + "?_t=" + d
        }
        else {
            u = u + "&_t=" + d
        }
        this.load(w, h);
        $("#FB_dialog_content").append($('<iframe id="FB_dialog_iframe" name="FB_dialog_iframe" scrolling="no" frameborder="0"></iframe>').hide());
        $("#FB_dialog_iframe").attr("src", u);
        var self = this;
        var iframe = document.getElementById("FB_dialog_iframe");
        if (iframe.attachEvent) {
            iframe.attachEvent("onload",
         function () {
             $("#FB_dialog_loading").remove();
             $("#FB_dialog_iframe").show();
             if (w == "auto") {
                 if (iframe.contentDocument && iframe.contentDocument.body.offsetWidth) {
                     w = iframe.contentDocument.body.offsetWidth;
                 } else {
                     w = iframe.Document.body.scrollWidth;
                 }
             }
             if (h == "auto") {

                 if (iframe.contentDocument && iframe.contentDocument.body.offsetHeight) {
                     h = iframe.contentDocument.body.offsetHeight;
                 } else {
                     h = iframe.Document.body.scrollHeight;
                 }
             }
			 

             self.resize(w, h);
             self.position();
             //self.title(t);
         });
        }
        else {
            iframe.onload = function () {
                $("#FB_dialog_loading").remove();
                $("#FB_dialog_iframe").show();
                if (w == "auto") {
                    if (iframe.contentDocument && iframe.contentDocument.body.offsetWidth) {
                        w = iframe.contentDocument.body.offsetWidth;
                    } else {
                        w = iframe.Document.body.scrollWidth;
                    }
                }
                if (h == "auto") {

                    if (iframe.contentDocument && iframe.contentDocument.body.offsetHeight) {
                        h = iframe.contentDocument.body.offsetHeight;
                    } else {
                        h = iframe.Document.body.scrollHeight;
                    }
                }

                self.resize(w, h);
                self.position();
              //  self.title(t);
            };
        }




        return this;
    },
    openHtml: function (str, t, w, h) {
        this.init();
        this.load(w, h);
        //this.title(t);
        if (str != undefined) {
            $("#FB_dialog_content").html(str);
            this.resize(w, h);

            this.position();
        }
        return this;
    },
    openAjax: function (url, parm, t, w, h, fun) {
        var self = this;
        this.init();
        this.load(w, h);
        this.title(t);
        $.ajax({
            type: "get",
            url: url,
            data: parm,
            cache: false,
            success: function (result) {
                if (result != null) {
                    $("#FB_dialog_content").html(result);
                    self.resize(w, h);
                    self.position();

                    if ($.isFunction(fun)) {
                        fun();
                    }
                }
            }
        });
    },
    Alert: function (str) {
        var self = this;
        this.init();
        this.insertHtml('<div class="popTop"><span class="adel" onclick="dialog.close();"></span>提示</div><div class="clearfix popErrorContent" >' + str + '</div><div class="popBottom"><input type="button"  value="确 定" onclick="dialog.close();" /></div>');
        this.load();
    },
    Confirm: function (str, fun) {
        var self = this;
        this.init();
        this.insertHtml('<div class="popTop"><span class="adel" onclick="dialog.close();"></span>提示</div><div class="clearfix popErrorContent" style="width:400px;" >' + str + '</div><div class="popBottom"><div style="float:left; margin-left:70px;"><input type="button"  value="确 定" id="Dialog_btn_Ok" /></div><div style="float:left; margin-left:30px;"><input type="button"  value="取 消" onclick="dialog.close();" /></div></div>');
        
        if ($.isFunction(fun)) {
            $("#Dialog_btn_Ok").bind("click", function () { fun() });
        }
    },
    load: function (w, h) {
        //this.title("等待...");
        //this.resize(w, "auto");
        this.position();
        $("#FB_dialog").show();
        return this;
    },
    insertHtml: function (str) {
        $("#FB_dialog_content").html(str);
        this.resize("auto", "auto");
        this.position();
    },
    textArea:function(str,fun){
    	var self = this;
        this.init();
        this.insertHtml('<div class="popTop"><span class="adel" onclick="dialog.close();"></span>提示</div><div class="clearfix popErrorContent" style="width:350px;" >' + str + '</div><div class="popBottom"><div style="float:left; margin-left:70px;"><input type="button"  value="确 定" id="Dialog_btn_Ok" /></div><div style="float:left; margin-left:30px;"><input type="button"  value="取 消" onclick="dialog.close();" /></div></div>');
        
        if ($.isFunction(fun)) {
            $("#Dialog_btn_Ok").bind("click", function () { fun() });
        }
    }
}