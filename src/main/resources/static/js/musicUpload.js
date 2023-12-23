function handleDragOver(event) {
    event.preventDefault();
    document.getElementById('drop-area').classList.add('highlight');
}

function handleDragLeave(event) {
    event.preventDefault();
    document.getElementById('drop-area').classList.remove('highlight');
}

function handleDrop(event) {
    event.preventDefault();
    document.getElementById('drop-area').classList.remove('highlight');

    var files = event.dataTransfer.files;
    handleFiles(files);
}

function openFileInput() {
    document.getElementById('file-input').click();
}

function handleFileSelect(event) {
    var files = event.target.files;
    handleFiles(files);
}

function handleFiles(files) {
    var fileNameDisplay = document.getElementById('file-name');

    if (files.length > 0) {
        fileNameDisplay.innerText = '你要上传的文件是： ' + files[0].name;
    } else {
        fileNameDisplay.innerText = '';
    }
}

function validateForm() {
    // Perform any additional form validation here
    return true;
}