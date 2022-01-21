<?php
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';

class Makanan extends REST_Controller{

    //declare constructor
    function __construct(){
        parent::__construct();
        $this->load->model('Makanan_model');
    }

    //function get/show data makanan
    function index_get(){
        //cek token
        //$this->token_check();

        //value paramater nama
        $nama = $this->get('nama');
        //call function getMakanan from model
        $data = $this->Makanan_model->getMakanan($nama);

        /*response / result
        $result = array(
            'success' => true,
            'message' => 'data found',
            'data' => array('pengguna' => $data)
        );*/
        $result = $data;

        //show response
        $this->response($result, REST_Controller::HTTP_OK);
    }    

    //function insert (POST) makanan
    function index_post(){
        //cek token
        //$this->token_check();

        //validasi jika inputan kosong/format tidak sesuai
        $validasi_message = [];

        //jika id kosong
        if($this->post('id') == ''){
            array_push($validasi_message,'ID can not be empty');
        }

        //jika nama kosong
        if($this->post('nama') == '' ){
            array_push($validasi_message,'Name can not be blank');
        }

        //jika harga kosong
        if($this->post('harga') == '' ){
            array_push($validasi_message,'Price can not be empty');
        }

        //upload image
        $part = "./foto/";
        $filename = "img".rand(9,9999).".jpg";

        if($filename == '' ){
            array_push($validasi_message,'Image can not be empty');
        }

        //show message
        if(count($validasi_message) > 0){
            $output = array(
                'success' => false,
                'message' => 'insert data failed, data not valid',
                'data' => $validasi_message
            );

            $this->response($output, REST_Controller::HTTP_OK);
            $this->output->_display();
            exit();
        }

        $data = array(
            'id_makanan' => $this->post('id'),
            'nama_makanan' => $this->post('nama'),
            'harga' => $this->post('harga'),
            'gambar' => $this->post('image'),
            'status' => ('Aktif')
        );

        //call function insertPengguna from model
        $result = $this->Makanan_model->insertMakanan($data);

        //response
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
                'data' => array(
                    'makanan' => $result
                )
            );
        }
        $this->response($output, REST_Controller::HTTP_OK);
    }

    //function for update (PUT) data pengguna
    function index_put(){
        //cek token
        //$this->token_check();

        //get id makanan
        $id = $this->put('id');

        //validasi jika inputan kosong/format tidak sesuai
        $validasi_message = [];

        //jika id kosong
        if($id == ''){
            array_push($validasi_message,'ID can not be empty');
        }

        //jika nama kosong
        if($this->put('nama') == ''){
            array_push($validasi_message,'name can not be empty');
        }

        //jika harga kosong
        if($this->put('harga') == '' ){
            array_push($validasi_message,'price can not be blank');
        }

        //jika gambar kosong
        if($this->put('image') == '' ){
            array_push($validasi_message,'image can not be empty');
        }

        //show message
        if(count($validasi_message) > 0){
            $output = array(
                'success' => false,
                'message' => 'update data failed, data not valid',
                'data' => $validasi_message
            );

            $this->response($output, REST_Controller::HTTP_OK);
            $this->output->_display();
            exit();
        }

        //data yang akan diupdate
        $data = array(
            'nama_makanan' => $this->put('nama'),
            'harga' => $this->put('harga'),
            'gambar' => $this->put('image')
        );

        //call function updatePengguna from model
        $result = $this->Makanan_model->updateMakanan($data, $id);

        //response
        $output = array(
            'success' => true,
            'message' => 'update data success',
            'data' => array(
                'makanan' => $result
            )
        );

        $this->response($output, REST_Controller::HTTP_OK);
    }

    //function delete (DELETE) data makanan
    function index_delete(){
        //cek token
        //$this->token_check();

        //get id
        $id = $this->delete('id');

        //validasi input kosong/tdk
        $validasi_message = [];

        //jika username kosong
        if($id == ''){
            array_push($validasi_message,'id can not be empty');
        }

        //show message
        if(count($validasi_message) > 0){
            $output = array(
                'success' => false,
                'message' => 'delete data failed, ID is invalid',
                'data' => $validasi_message
            );

            $this->response($output, REST_Controller::HTTP_OK);
            $this->output->_display();
            exit();
        }

        //call deletePengguna from model
        $result = $this->Makanan_model->deleteMakanan($id);

        //cek result
        if(empty($result)){
            $output = array(
                'success' => false,
                'message' => 'id not found',
                'data' => null
            );
        }else{
            $output = array(
                'success' => true,
                'message' => 'delete data success',
                'data' => array(
                    'makanan' => $result
                )
            );
        }

        $this->response($output, REST_Controller::HTTP_OK);
    }

    //function cek token
    function token_check(){
        try{
            $token = $this->input->get_request_header('Authorization');

            if(!empty($token)){
                $token = explode(' ', $token)[1];
            }

            //instance class JWT
            $jwt = new JWT();

            //declare secret key
            $secret_key = 'JKq5GpLkbCr39JlAqTRc56JHsblOjan90PsnamS312KSlkfd';

            $token_decode = $jwt->decode($token, $secret_key);
        }catch(Exception $e){
            $result = array(
                'success' => false,
                'message' => 'token is invalid',
                'data' => null,
                'error_code' => 1204
            );
            $this->response($result, REST_Controller::HTTP_OK);
            $this->output->_display();
            exit();
        }
    }
}