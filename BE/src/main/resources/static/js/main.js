
    $(document).ready(function () {
    $.getJSON('https://esgoo.net/api-tinhthanh/1/0.htm', function (data_tinh) {
        if (data_tinh.error == 0) {
            $.each(data_tinh.data, function (key_tinh, val_tinh) {
                $("#tinh").append('<option value="' + val_tinh.id + '">' + val_tinh.full_name + '</option>');
            });

            $("#tinh").change(function (e) {
                var selectedTinh = $(this).find("option:selected").text();
                var idtinh = $(this).val();
                $('#selectedTinhName').val(selectedTinh);

                // Clear huyen and xa dropdowns and input fields
                $("#huyen").html('<option value="0">Chọn quận huyện</option>');
                $("#selectHuyenName").val('');
                $("#xa").html('<option value="0">Chọn phường xã</option>');
                $("#selectedXaName").val('');

                // Fetch districts
                $.getJSON('https://esgoo.net/api-tinhthanh/2/' + idtinh + '.htm', function (data_quan) {
                    if (data_quan.error == 0) {
                        $.each(data_quan.data, function (key_quan, val_quan) {
                            $("#huyen").append('<option value="' + val_quan.id + '">' + val_quan.full_name + '</option>');
                        });

                        $("#huyen").off('change').on('change', function (e) {
                            var selectedHuyen = $(this).find("option:selected").text();
                            var idquan = $(this).val();
                            $('#selectHuyenName').val(selectedHuyen);

                            // Clear xa dropdown and input field
                            $("#xa").html('<option value="0">Chọn phường xã</option>');
                            $("#selectedXaName").val('');

                            // Fetch wards
                            $.getJSON('https://esgoo.net/api-tinhthanh/3/' + idquan + '.htm', function (data_phuong) {
                                if (data_phuong.error == 0) {
                                    $.each(data_phuong.data, function (key_phuong, val_phuong) {
                                        $("#xa").append('<option value="' + val_phuong.id + '">' + val_phuong.full_name + '</option>');
                                    });

                                    $("#xa").off('change').on('change', function (e) {
                                        var selectedXa = $(this).find("option:selected").text();
                                        $('#selectedXaName').val(selectedXa);
                                    });
                                }
                            });
                        });
                    }
                });
            });
        }
    })
});
