package com.example.augen.demo.service.imp;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.augen.demo.service.Identifier;

@Service
public class IdentifierImp implements Identifier {

	private AtomicLong counter = new AtomicLong(0);

	@Override
	public long getIdentifier() {
		return counter.addAndGet(1);
	}

}
