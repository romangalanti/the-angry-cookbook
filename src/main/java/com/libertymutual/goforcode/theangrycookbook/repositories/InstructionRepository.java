package com.libertymutual.goforcode.theangrycookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.theangrycookbook.models.Instruction;



@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {

}
