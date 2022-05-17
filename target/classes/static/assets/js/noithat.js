function deleteNT(id) {
    Swal.fire({
        title: 'Bạn có chắc chắn muốn xoá?',
        text: 'Dữ liệu sẽ không thể khôi phục được!',
        icon: 'warning',

        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        cancelButtonText: 'Không',
        confirmButtonText: 'Có'
    }).then((result) => {
        if (result.isConfirmed) {

            Swal.fire({
                title: 'Đã xoá!',
                text: 'Bạn đã xoá thành công!',
                icon: 'success',
            }).then(function (){
                window.location.href='/admin/furniture-management/' +id+'/remove'
            })
        }
    })
}