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
                            '<ul class="qq-upload-list" ></ul>' +
                          '</div>',
                fileTemplate: '<li>' +
                       '<div class="qq-progress-bar"></div>' +
                          '<span class="qq-upload-spinner"></span>' +
                          '<span class="qq-upload-finished"></span>' +
                          '<span class="qq-upload-file"></span>' +
                          '<span class="qq-upload-size"></span>' +
                          '<span class="qq-upload-id"></span>' +
                          '<a class="qq-upload-cancel" href="#">{cancelButtonText}</a>' +
                          '<a class="qq-upload-retry" href="#">{retryButtonText}</a>' +
                          '<a class="qq-upload-delete" href="#">{deleteButtonText}</a>' +
                          '<span class="qq-upload-status-text">{statusText}</span>' +
                          '</li>',
              classes: {
                success: 'alert alert-success',
                fail: 'alert alert-error'
              }
            }).on('complete', function(event, id, fileName, response) {
                var $fileItemInDom = $(this).fineUploader("getItemByFileId", id);
                    thumbnailUrl = "/bhg-photos/photo/" + response.id + "/150/175";
                    image = new Image();
                    image.src = thumbnailUrl;
                    $(image).insertBefore($fileItemInDom);
                    console.log("File uploaded: " + id + " " + fileName);
        });
    });