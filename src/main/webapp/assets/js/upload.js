$(document).ready(function() {
		console.log("Document ready...");
		
		$('#fine-uploader').fineUploader({
	          request: {
	            endpoint: 'upload',
	            customHeaders: { Accept: 'application/json' }
	          },
	          text: {
	            uploadButton: '<div><i class="icon-upload icon-white"></i> Test me now and upload a file</div>'
	          },
	          template: '<div class="qq-uploader span12">' +
	                      '<pre class="qq-upload-drop-area span12"><span>{dragZoneText}</span></pre>' +
	                      '<div class="qq-upload-button btn btn-success" style="width: auto;">{uploadButtonText}</div>' +
	                      '<span class="qq-drop-processing"><span>{dropProcessingText}</span><span class="qq-drop-processing-spinner"></span></span>' +
	                      '<ul class="qq-upload-list" style="margin-top: 10px; text-align: center;"></ul>' +
	                    '</div>',
	          classes: {
	            success: 'alert alert-success',
	            fail: 'alert alert-error'
	          }
	        }).on('complete', function(event, id, fileName, responseJSON) {
	        	$('#thumbnails').prepend('<img src="/bhg-photos/photo/' + responseJSON.id + '/150/175/" />');
	        	console.log("File uploaded: " + id + " " + fileName + " " + responseJSON.id);
		});
    });