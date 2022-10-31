package com.parkingslot.main.JwtSecurity;

import org.springframework.stereotype.Component;

@Component
public class JWTToken {
	
		private String token;

		public JWTToken(String token) {
			super();
			this.token = token;
		}

		public JWTToken() {
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}
		
		

}
