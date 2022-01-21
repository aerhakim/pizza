<?php
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';

class Detail_transaksi extends REST_Controller{
    //declare constructor
    function __construct(){
        parent::__construct();
        $this->load->model('Transaksi_model');
    }

    function index_post(){
        $data = array(
            'no_transaksi' => $this->post('no_transaksi'),
            'id_makanan' => $this->post('id_makanan'),
            'harga' => $this->post('harga'),
            'jumlah' => $this->post('jumlah')
        );

        $result = $this->Transaksi_model->insertDetailPenjualan($data);

        if(empty($result)){
            $output = array(
                'success' => false,
                'message' => 'data already exists',
                'data' => null
            );
        }else{
            $output = array(
                'success' => true,
                'message' => 'insert data success',
                'data' => $result
            );
        }

        $this->response($output, REST_Controller::HTTP_OK);


    }

}