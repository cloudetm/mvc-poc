package net.zburak.poc.domain;

import java.io.Serializable;

public interface Entity<I> extends Serializable {
	
	I getId();

	void setId(I id);
	
}
