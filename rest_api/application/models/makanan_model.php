<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Makanan_model extends CI_Model{
    
    //declare constructor
    function __construct(){
        parent::__construct();
        //akses database
        $this->load->database();
    }

    //function utk show data makanan (GET) & search by nama
    public function getMakanan($nama){
        if($nama==''){
            //show all data
            $data = $this->db->get('makanan');
        }else{
            //using like
            $this->db->like('nama_makanan', $nama);
            $data = $this->db->get('makanan');
        }

        return $data->result_array();
    }



    //function insert data makanan 
    public function insertMakanan($data){
        //cek apakah makanan yang diinputkan sudah ada/blm
        $this->db->where('nama_makanan', $data['nama_makanan']);
        $check_data = $this->db->get('makanan');
        $result = $check_data->result_array();

        if(empty($result)){
            //jika nama makanan belum ada, maka data ditambahkan ke tabel makanan
            $this->db->insert('makanan', $data);
        }else{
            $data = array();
        }

        return $data;
    }

    //function update data makanan
    public function updateMakanan($data, $id){
        //update data dimana id_makanan memenuhi syarat
        $this->db->where('id_makanan', $id);
        $this->db->update('makanan', $data);

        $result = $this->db->get_where('makanan', array('id_makanan' => $id));

        return $result->row_array();
    }

    //function delete data makanan
    public function deleteMakanan($id){
        $result = $this->db->get_where('makanan', array('id_makanan' => $id));
        //delete data
        $this->db->where('id_makanan', $id);
        $this->db->delete('makanan');

        return $result->row_array();
    }
}