<?php
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';

class Transaksi extends REST_Controller{
    //declare constructor
    function __construct(){
        parent::__construct();
        $this->load->model('Transaksi_model');
    }

    function index_post(){
        $data = array(
            'tanggal' => $this->post('tanggal'),
            'username' => $this->post('username'),
        );

        $result = $this->Transaksi_model->insertPenjualan($data);

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