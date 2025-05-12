package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// create table public.customers (
//   id serial not null
//   , name text
//   , address text
//   , tel text
//   , email text
// );


@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "tel")
    private String tel;
    @Column(name = "email")
    private String email;


        public Customer() {
    }


        public Customer(String name, String address, String tel, String email) {
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.email = email;
    }

        

        public String getName() {
            return name;
        }


        public void setName(String name) {
            this.name = name;
        }


        public String getAddress() {
            return address;
        }


        public void setAddress(String address) {
            this.address = address;
        }


        public String getTel() {
            return tel;
        }


        public void setTel(String tel) {
            this.tel = tel;
        }


        public String getEmail() {
            return email;
        }


        public void setEmail(String email) {
            this.email = email;
        }


        public Integer getId() {
            return id;
        }


        public void setId(Integer id) {
            this.id = id;
        }

}
