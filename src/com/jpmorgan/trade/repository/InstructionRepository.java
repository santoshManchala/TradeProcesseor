package com.jpmorgan.trade.repository;

import com.jpmorgan.trade.model.Instruction;

import java.util.List;

public interface InstructionRepository {
    List<Instruction> getInstructions();
}
