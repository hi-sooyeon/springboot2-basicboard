var board = {
    init : function () {
        var _this = this;
        $('#btnSave').on('click', function () {
            _this.save();
        });

        $('#btnEdit').on('click', function () {
            _this.update();
        });

    },
    save : function () {
        var frm = $('#saveFrm')[0];
        var data = new FormData(frm);

        $('#btnSave').prop('disabled', true);

        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: '/posts',
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 60000
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var frm = $('#editFrm')[0];
        var data = new FormData(frm);

        $('#btnEdit').prop('disabled', true);

        $.ajax({
            type: 'PUT',
            enctype: 'multipart/form-data',
            url: '/posts/edit/' + id,
            data: data,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 60000
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

board.init();