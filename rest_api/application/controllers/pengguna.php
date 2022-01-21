<?php
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';

class Pengguna extends REST_Controller{

    //declare constructor
    function __construct(){
        parent::__construct();
        $this->load->model('Pengguna_model');
    }

    //function get/show data pengguna
    function index_get(){
        //cek token
        //$this->token_check();

        //value paramater nama
        $nama = $this->get('nama');
        //call function getPengguna from model
        $data = $this->Pengguna_model->getPengguna($nama);

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

    //function insert (POST) pengguna
    function index_post(){
        //cek token
        //$this->token_check();

        //validasi jika inputan kosong/format tidak sesuai
        $validasi_message = [];

        //jika username kosong
        if($this->post('username') == ''){
            array_push($validasi_message,'Email (username) can not be empty');
        }

        //jika format username (email) tidak sesuai
        if($this->post('username') != '' && !filter_var($this->post('username'), FILTER_VALIDATE_EMAIL)){
            array_push($validasi_message,'Email is invalid');
        }

        //jika nama kosong
        if($this->post('nama') == '' ){
            array_push($validasi_message,'Name can not be blank');
        }

        //jika level kosong
        if($this->post('level') == '' ){
            array_push($validasi_message,'Level can not be empty');
        }

        //jika password kosong
        if($this->post('password') == '' ){
            array_push($validasi_message,'Password can not be empty');
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
            'username' => $this->post('username'),
            'nama' => $this->post('nama'),
            'level' => $this->post('level'),
            'password' => $this->post('password')
        );

        //call function insertPengguna from model
        $result = $this->Pengguna_model->insertPengguna($data);

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
                    'pengguna' => $result
                )
            );
        }
        $this->response($output, REST_Controller::HTTP_OK);
    }

    //function for update (PUT) data pengguna
    function index_put(){
        //cek token
        //$this->token_check();

        //get username
        $username = $this->put('username');

        //validasi jika inputan kosong/format tidak sesuai
        $validasi_message = [];

        //jika username kosong
        if($username == ''){
            array_push($validasi_message,'Email (username) can not be empty');
        }

        //jika format username (email) tidak sesuai
        if($username != '' && !filter_var($username, FILTER_VALIDATE_EMAIL)){
            array_push($validasi_message,'Email is invalid');
        }

        //jika nama kosong
        if($this->put('nama') == '' ){
            array_push($validasi_message,'Name can not be blank');
        }

        //jika level kosong
        if($this->put('level') == '' ){
            array_push($validasi_message,'Level can not be empty');
        }

        //jika password kosong
        if($this->put('password') == '' ){
            array_push($validasi_message,'Password can not be empty');
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
            'nama' => $this->put('nama'),
            'level' => $this->put('level'),
            'password' => $this->put('password')
        );

        //call function updatePengguna from model
        $result = $this->Pengguna_model->updatePengguna($data, $username);

        //response
        $output = array(
            'success' => true,
            'message' => 'update data success',
            'data' => array(
                $result
            )
        );

        $this->response($output, REST_Controller::HTTP_OK);
    }

    //function delete (DELETE) data pengguna
    function index_delete(){
        //cek token
        //$this->token_check();

        //get username
        $username = $this->delete('username');

        //validasi input kosong/tdk
        $validasi_message = [];

        //jika username kosong
        if($username == ''){
            array_push($validasi_message,'Email (username) can not be empty');
        }

        //jika format username (email) tidak sesuai
        if($username != '' && !filter_var($username, FILTER_VALIDATE_EMAIL)){
            array_push($validasi_message,'Email is invalid');
        }

        //show message
        if(count($validasi_message) > 0){
            $output = array(
                'success' => false,
                'message' => 'delete data failed, email/username is invalid',
                'data' => $validasi_message
            );

            $this->response($output, REST_Controller::HTTP_OK);
            $this->output->_display();
            exit();
        }

        //call deletePengguna from model
        $result = $this->Pengguna_model->deletePengguna($username);

        //cek result
        if(empty($result)){
            $output = array(
                'success' => false,
                'message' => 'email/username not found',
                'data' => null
            );
        }else{
            $output = array(
                'success' => true,
                'message' => 'delete data success',
                'data' => array(
                    $result
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