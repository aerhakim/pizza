<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Pengguna_model extends CI_Model{

    //declare constructor
    function __construct(){
        parent::__construct();
        //akses database
        $this->load->database();
    }

    //function utk show data pengguna (GET) & search by nama/username
    public function getPengguna($nama){
        if($nama==''){
            //show all data
            $data = $this->db->get('pengguna');
        }else{
            //using like
            $this->db->like('nama', $nama);
            $this->db->or_like('username', $nama);
            $data = $this->db->get('pengguna');
        }

        return $data->result_array();
    }

    //function insert data pengguna 
    public function insertPengguna($data){
        //cek apakah username yang diinputkan sudah ada/blm
        $this->db->where('username', $data['username']);
        $check_data = $this->db->get('pengguna');
        $result = $check_data->result_array();

        if(empty($result)){
            //jika username belum ada, maka data ditambahkan ke tabel pengguna
            $this->db->insert('pengguna', $data);
        }else{
            $data = array();
        }

        return $data;
    }

    //function update data pengguna
    public function updatePengguna($data, $username){
        //update data dimana usernamenya memenuhi syarat
        $this->db->where('username', $username);
        $this->db->update('pengguna', $data);

        $result = $this->db->get_where('pengguna', array('username' => $username));

        return $result->row_array();
    }

    //function delete data pengguna
    public function deletePengguna($username){
        $result = $this->db->get_where('pengguna', array('username' => $username));
        //delete data
        $this->db->where('username', $username);
        $this->db->delete('pengguna');

        return $result->row_array();
    }

    //function login
    public function login($username, $password){
        //cek data
        $this->db->where('username', $username);
        $this->db->where('password', $password);
        $data = $this->db->get('pengguna');

        return $data->row_array();
    }
}