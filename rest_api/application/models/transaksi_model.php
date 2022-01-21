<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Transaksi_model extends CI_Model{

    //declare constructor
    function __construct(){
        parent::__construct();
        //akses database
        $this->load->database();
    }

    public function showNoTransaksi(){
        $last_id = $this->db->insert_id();
        return $last_id;
    }

    public function insertPenjualan($data){
        $this->db->insert('penjualan', $data);
        $last_id = $this->db->insert_id();
        $data['last_id'] = $last_id;

        return $data;
    }

    public function insertDetailPenjualan($data){
        $this->db->insert('detail_penjualan', $data);
        $last_id = $this->db->insert_id();
        $data['last_id'] = $last_id;

        return $data;
    }
}


