package com.bridgelabz.demo.model

import lombok.Data
import org.hibernate.annotations.CreationTimestamp
import java.util.*
import javax.persistence.*
@Entity
@Table(name="customer")
@Data
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Int=0;
    var firstname:String = "";
    var lastname:String = "";
    var email:String="";
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    lateinit var creationalTime:Date;
    var password:String="";
}