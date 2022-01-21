<?php
defined('BASEPATH') OR exit('No direct script access allowed');

require APPPATH . 'libraries/REST_Controller.php';

class Login extends REST_Controller{
    //declare constructor
    function __construct(){
        parent::__construct();
        $this->load->model('Pengguna_model');
    }

    function index_post(){
        //isi value parameter username dan password
        $username = $this->post('username');
        $password = $this->post('password');

        //load model -> login function
        $data = $this->Pengguna_model->login($username, $password);

        //response
        if(empty($data)){
            $output = array(
                'success' => false,
                'message' => 'username/password is invalid',
                'data' => null,
                'error_code' => 1308
            );
            $this->response($output, REST_Controller::HTTP_OK);
            $this->output->_display();
            exit();
        }else{
            //instance class JWT (jwt_helper)
            $jwt = new JWT();

            //declare secret key
            $secret_key = 'JKq5GpLkbCr39JlAqTRc56JHsblOjan90PsnamS312KSlkfd';

            //get date now (current time)
            $date = new Datetime();
            //set payload
            $payload = array(
                'username' => $data['username'],
                'name' => $data['nama'],
                'user type' => $data['level'],
                'login_time' => $date->getTimeStamp(),
                'expired_time' => $date->getTimeStamp() + 1800
            );

            
            $result = array(
                'success' => true,
                'message' => 'login success',
                'data' => $data,
                'token' => $jwt->encode($payload, $secret_key)
            );
            //$result = $data;
            $this->response($result, REST_Controller::HTTP_OK);
        }
    }
}