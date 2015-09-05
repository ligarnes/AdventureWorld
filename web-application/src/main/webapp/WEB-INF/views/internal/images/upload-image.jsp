<label class="control-label">Select File</label>
<input id="input-4" type="file" multiple="true" class="file-loading">
<script>
$(document).on('ready', function() {
    $("#input-4").fileinput({showCaption: false});
});
</script>