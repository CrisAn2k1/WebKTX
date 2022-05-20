function deleteHSCP(id) {
    Swal.fire({
        title: 'Bạn có chắc chắn muốn huỷ bỏ đơn chuyển phòng này?',
        icon: 'warning',

        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        cancelButtonText: 'Không',
        confirmButtonText: 'Hủy bỏ'
    }).then((result) => {
        if (result.isConfirmed) {

            Swal.fire({
                title: 'Đã huỷ bỏ thành công!',
                icon: 'success',
            }).then(function (){
                window.location.href='/admin/hosochuyenphong-management/' +id+'/remove'
            })
        }
    })
}