jQuery.extend({
	createDownloadFrame : function(id, setting) {
		var frameId = 'jUploadFrame' + id;
		var frame = $("<iframe></iframe>").attr({
			"src":"",
			"name":frameId,
			"id":frameId
		}).css("display","none").appendTo('body');
		return frame;
	},
	createDownloadForm : function(id, setting) {
		var action = setting.url;
		var target = setting.target;
		var formId = 'jUploadForm' + id;
		var form = $('<form></form>').attr({
			action : action,
			method : "POST",
			name : formId,
			id : formId,
			target : target
		}).css('display','none').appendTo('body');
		var params = setting.param;
		if(params != null){
			for(var name in params){
				$("<input />").attr({name:name,value:params[name],type:'hidden'}).appendTo(form);
			}
		}
		return form;
	},
	ajaxDownload : function(s) {
		s = jQuery.extend({}, s);
		var id = new Date().getTime();
		var form = jQuery.createDownloadForm(id, s);
		form.submit();
		form.remove();
	}
});
