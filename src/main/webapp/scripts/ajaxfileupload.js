jQuery.extend({
	createUploadFrame : function(id, setting) {
		var frameId = 'jUploadFrame' + id;
		var frame = $("<iframe></iframe>").attr({
			"src":"",
			"name":frameId,
			"id":frameId
		}).css("display","none").appendTo('body');
		return frame;
	},
	createUploadForm : function(id, setting, iframe) {
		var fileElementId = setting.fileElementId;
		var action = setting.url;
		var target = iframe.attr("id");
		var formId = 'jUploadForm' + id;
		var form = $('<form></form>').attr({
			action : action,
			method : "POST",
			name : formId,
			id : formId,
			target : target,
			encoding : "multipart/form-data",
			enctype : "multipart/form-data"
		}).css('display','none').appendTo('body');
		for(var i = 0; i < fileElementId.length; i++){
			var tempId = fileElementId[i];
			var fileId = 'jUploadFile' + new Date().getTime();
			var oldElement = $('#' + tempId);
			var newElement = $(oldElement).clone();
			$(oldElement).attr('id', fileId).attr("name", tempId);
			$(oldElement).before(newElement);
			$(oldElement).appendTo(form);
		}
		var params = setting.param;
		if(params != null){
			for(var name in params){
				$("<input />").attr({name:name,value:params[name],type:'hidden'}).appendTo(form);
			}
		}
		return form;
	},
	ajaxFileUpload : function(s) {
		s = jQuery.extend({}, jQuery.ajaxSettings, s);
		var id = new Date().getTime();
		var iframe = jQuery.createUploadFrame(id, s);
		var form = jQuery.createUploadForm(id, s, iframe);
		var io = iframe.get(0);
		if (s.global && !jQuery.active++) {
			jQuery.event.trigger("ajaxStart");
		}
		var xml = {};
		if (s.global)
			jQuery.event.trigger("ajaxSend", [ xml, s ]);
		var uploadCallback = function() {
			try {
				if (io.contentWindow) {
					xml.responseText = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
					xml.responseXML = io.contentWindow.document.XMLDocument ? io.contentWindow.document.XMLDocument : io.contentWindow.document;
				} else if (io.contentDocument) {
					xml.responseText = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
					xml.responseXML = io.contentDocument.document.XMLDocument ? io.contentDocument.document.XMLDocument : io.contentDocument.document;
				}
			} catch (e) {
				
			}
			if (xml) {
				var data = eval('(' + xml.responseText + ')');
				if (s.success)
					s.success(data);
				if (s.global)
					jQuery.event.trigger("ajaxSuccess", [ xml, s ]);
				if (s.global)
					jQuery.event.trigger("ajaxComplete", [ xml, s ]);
				if (s.global && !--jQuery.active)
					jQuery.event.trigger("ajaxStop");
				jQuery(io).unbind();
				setTimeout(function() {
					form.remove();
					iframe.remove();
				}, 100);
				xml = null;
			}
		};
		if (window.attachEvent) {
			io.attachEvent('onload', uploadCallback);
		} else {
			io.addEventListener('load', uploadCallback, false);
		}
		form.submit();
	}
});
