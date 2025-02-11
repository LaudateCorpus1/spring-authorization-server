/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.oauth2.server.authorization.client;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.lang.Nullable;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.Version;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * A representation of a client registration with an OAuth 2.0 Authorization Server.
 *
 * @author Joe Grandja
 * @author Anoop Garlapati
 * @see <a target="_blank" href="https://tools.ietf.org/html/rfc6749#section-2">Section 2 Client Registration</a>
 * @since 0.0.1
 */
public class RegisteredClient implements Serializable {
	private static final long serialVersionUID = Version.SERIAL_VERSION_UID;
	private String id;
	private String clientId;
	private Instant clientIdIssuedAt;
	private String clientSecret;
	private Instant clientSecretExpiresAt;
	private String clientName;
	private Set<ClientAuthenticationMethod> clientAuthenticationMethods;
	private Set<AuthorizationGrantType> authorizationGrantTypes;
	private Set<String> redirectUris;
	private Set<String> scopes;
	private ClientSettings clientSettings;
	private TokenSettings tokenSettings;

	protected RegisteredClient() {
	}

	/**
	 * Returns the identifier for the registration.
	 *
	 * @return the identifier for the registration
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Returns the client identifier.
	 *
	 * @return the client identifier
	 */
	public String getClientId() {
		return this.clientId;
	}

	/**
	 * Returns the time at which the client identifier was issued.
	 *
	 * @return the time at which the client identifier was issued
	 */
	@Nullable
	public Instant getClientIdIssuedAt() {
		return this.clientIdIssuedAt;
	}

	/**
	 * Returns the client secret or {@code null} if not available.
	 *
	 * @return the client secret or {@code null} if not available
	 */
	@Nullable
	public String getClientSecret() {
		return this.clientSecret;
	}

	/**
	 * Returns the time at which the client secret expires or {@code null} if it does not expire.
	 *
	 * @return the time at which the client secret expires or {@code null} if it does not expire
	 */
	@Nullable
	public Instant getClientSecretExpiresAt() {
		return this.clientSecretExpiresAt;
	}

	/**
	 * Returns the client name.
	 *
	 * @return the client name
	 */
	public String getClientName() {
		return this.clientName;
	}

	/**
	 * Returns the {@link ClientAuthenticationMethod authentication method(s)} that the client may use.
	 *
	 * @return the {@code Set} of {@link ClientAuthenticationMethod authentication method(s)}
	 */
	public Set<ClientAuthenticationMethod> getClientAuthenticationMethods() {
		return this.clientAuthenticationMethods;
	}

	/**
	 * Returns the {@link AuthorizationGrantType authorization grant type(s)} that the client may use.
	 *
	 * @return the {@code Set} of {@link AuthorizationGrantType authorization grant type(s)}
	 */
	public Set<AuthorizationGrantType> getAuthorizationGrantTypes() {
		return this.authorizationGrantTypes;
	}

	/**
	 * Returns the redirect URI(s) that the client may use in redirect-based flows.
	 *
	 * @return the {@code Set} of redirect URI(s)
	 */
	public Set<String> getRedirectUris() {
		return this.redirectUris;
	}

	/**
	 * Returns the scope(s) that the client may use.
	 *
	 * @return the {@code Set} of scope(s)
	 */
	public Set<String> getScopes() {
		return this.scopes;
	}

	/**
	 * Returns the {@link ClientSettings client configuration settings}.
	 *
	 * @return the {@link ClientSettings}
	 */
	public ClientSettings getClientSettings() {
		return this.clientSettings;
	}

	/**
	 * Returns the {@link TokenSettings token configuration settings}.
	 *
	 * @return the {@link TokenSettings}
	 */
	public TokenSettings getTokenSettings() {
		return this.tokenSettings;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		RegisteredClient that = (RegisteredClient) obj;
		return Objects.equals(this.id, that.id) &&
				Objects.equals(this.clientId, that.clientId) &&
				Objects.equals(this.clientIdIssuedAt, that.clientIdIssuedAt) &&
				Objects.equals(this.clientSecret, that.clientSecret) &&
				Objects.equals(this.clientSecretExpiresAt, that.clientSecretExpiresAt) &&
				Objects.equals(this.clientName, that.clientName) &&
				Objects.equals(this.clientAuthenticationMethods, that.clientAuthenticationMethods) &&
				Objects.equals(this.authorizationGrantTypes, that.authorizationGrantTypes) &&
				Objects.equals(this.redirectUris, that.redirectUris) &&
				Objects.equals(this.scopes, that.scopes) &&
				Objects.equals(this.clientSettings, that.clientSettings) &&
				Objects.equals(this.tokenSettings, that.tokenSettings);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.clientId, this.clientIdIssuedAt, this.clientSecret, this.clientSecretExpiresAt,
				this.clientName, this.clientAuthenticationMethods, this.authorizationGrantTypes, this.redirectUris,
				this.scopes, this.clientSettings, this.tokenSettings);
	}

	@Override
	public String toString() {
		return "RegisteredClient {" +
				"id='" + this.id + '\'' +
				", clientId='" + this.clientId + '\'' +
				", clientName='" + this.clientName + '\'' +
				", clientAuthenticationMethods=" + this.clientAuthenticationMethods +
				", authorizationGrantTypes=" + this.authorizationGrantTypes +
				", redirectUris=" + this.redirectUris +
				", scopes=" + this.scopes +
				", clientSettings=" + this.clientSettings +
				", tokenSettings=" + this.tokenSettings +
				'}';
	}

	/**
	 * Returns a new {@link Builder}, initialized with the provided registration identifier.
	 *
	 * @param id the identifier for the registration
	 * @return the {@link Builder}
	 */
	public static Builder withId(String id) {
		Assert.hasText(id, "id cannot be empty");
		return new Builder(id);
	}

	/**
	 * Returns a new {@link Builder}, initialized with the values from the provided {@link RegisteredClient}.
	 *
	 * @param registeredClient the {@link RegisteredClient} used for initializing the {@link Builder}
	 * @return the {@link Builder}
	 */
	public static Builder from(RegisteredClient registeredClient) {
		Assert.notNull(registeredClient, "registeredClient cannot be null");
		return new Builder(registeredClient);
	}

	/**
	 * A builder for {@link RegisteredClient}.
	 */
	public static class Builder implements Serializable {
		private static final long serialVersionUID = Version.SERIAL_VERSION_UID;
		private String id;
		private String clientId;
		private Instant clientIdIssuedAt;
		private String clientSecret;
		private Instant clientSecretExpiresAt;
		private String clientName;
		private Set<ClientAuthenticationMethod> clientAuthenticationMethods = new HashSet<>();
		private Set<AuthorizationGrantType> authorizationGrantTypes = new HashSet<>();
		private Set<String> redirectUris = new HashSet<>();
		private Set<String> scopes = new HashSet<>();
		private ClientSettings clientSettings;
		private TokenSettings tokenSettings;

		protected Builder(String id) {
			this.id = id;
		}

		protected Builder(RegisteredClient registeredClient) {
			this.id = registeredClient.id;
			this.clientId = registeredClient.clientId;
			this.clientIdIssuedAt = registeredClient.clientIdIssuedAt;
			this.clientSecret = registeredClient.clientSecret;
			this.clientSecretExpiresAt = registeredClient.clientSecretExpiresAt;
			this.clientName = registeredClient.clientName;
			if (!CollectionUtils.isEmpty(registeredClient.clientAuthenticationMethods)) {
				this.clientAuthenticationMethods.addAll(registeredClient.clientAuthenticationMethods);
			}
			if (!CollectionUtils.isEmpty(registeredClient.authorizationGrantTypes)) {
				this.authorizationGrantTypes.addAll(registeredClient.authorizationGrantTypes);
			}
			if (!CollectionUtils.isEmpty(registeredClient.redirectUris)) {
				this.redirectUris.addAll(registeredClient.redirectUris);
			}
			if (!CollectionUtils.isEmpty(registeredClient.scopes)) {
				this.scopes.addAll(registeredClient.scopes);
			}
			this.clientSettings = ClientSettings.withSettings(registeredClient.getClientSettings().getSettings()).build();
			this.tokenSettings = TokenSettings.withSettings(registeredClient.getTokenSettings().getSettings()).build();
		}

		/**
		 * Sets the identifier for the registration.
		 *
		 * @param id the identifier for the registration
		 * @return the {@link Builder}
		 */
		public Builder id(String id) {
			this.id = id;
			return this;
		}

		/**
		 * Sets the client identifier.
		 *
		 * @param clientId the client identifier
		 * @return the {@link Builder}
		 */
		public Builder clientId(String clientId) {
			this.clientId = clientId;
			return this;
		}

		/**
		 * Sets the time at which the client identifier was issued.
		 *
		 * @param clientIdIssuedAt the time at which the client identifier was issued
		 * @return the {@link Builder}
		 */
		public Builder clientIdIssuedAt(Instant clientIdIssuedAt) {
			this.clientIdIssuedAt = clientIdIssuedAt;
			return this;
		}

		/**
		 * Sets the client secret.
		 *
		 * @param clientSecret the client secret
		 * @return the {@link Builder}
		 */
		public Builder clientSecret(String clientSecret) {
			this.clientSecret = clientSecret;
			return this;
		}

		/**
		 * Sets the time at which the client secret expires or {@code null} if it does not expire.
		 *
		 * @param clientSecretExpiresAt the time at which the client secret expires or {@code null} if it does not expire
		 * @return the {@link Builder}
		 */
		public Builder clientSecretExpiresAt(Instant clientSecretExpiresAt) {
			this.clientSecretExpiresAt = clientSecretExpiresAt;
			return this;
		}

		/**
		 * Sets the client name.
		 *
		 * @param clientName the client name
		 * @return the {@link Builder}
		 */
		public Builder clientName(String clientName) {
			this.clientName = clientName;
			return this;
		}

		/**
		 * Adds an {@link ClientAuthenticationMethod authentication method}
		 * the client may use when authenticating with the authorization server.
		 *
		 * @param clientAuthenticationMethod the authentication method
		 * @return the {@link Builder}
		 */
		public Builder clientAuthenticationMethod(ClientAuthenticationMethod clientAuthenticationMethod) {
			this.clientAuthenticationMethods.add(clientAuthenticationMethod);
			return this;
		}

		/**
		 * A {@code Consumer} of the {@link ClientAuthenticationMethod authentication method(s)}
		 * allowing the ability to add, replace, or remove.
		 *
		 * @param clientAuthenticationMethodsConsumer a {@code Consumer} of the authentication method(s)
		 * @return the {@link Builder}
		 */
		public Builder clientAuthenticationMethods(
				Consumer<Set<ClientAuthenticationMethod>> clientAuthenticationMethodsConsumer) {
			clientAuthenticationMethodsConsumer.accept(this.clientAuthenticationMethods);
			return this;
		}

		/**
		 * Adds an {@link AuthorizationGrantType authorization grant type} the client may use.
		 *
		 * @param authorizationGrantType the authorization grant type
		 * @return the {@link Builder}
		 */
		public Builder authorizationGrantType(AuthorizationGrantType authorizationGrantType) {
			this.authorizationGrantTypes.add(authorizationGrantType);
			return this;
		}

		/**
		 * A {@code Consumer} of the {@link AuthorizationGrantType authorization grant type(s)}
		 * allowing the ability to add, replace, or remove.
		 *
		 * @param authorizationGrantTypesConsumer a {@code Consumer} of the authorization grant type(s)
		 * @return the {@link Builder}
		 */
		public Builder authorizationGrantTypes(Consumer<Set<AuthorizationGrantType>> authorizationGrantTypesConsumer) {
			authorizationGrantTypesConsumer.accept(this.authorizationGrantTypes);
			return this;
		}

		/**
		 * Adds a redirect URI the client may use in a redirect-based flow.
		 *
		 * @param redirectUri the redirect URI
		 * @return the {@link Builder}
		 */
		public Builder redirectUri(String redirectUri) {
			this.redirectUris.add(redirectUri);
			return this;
		}

		/**
		 * A {@code Consumer} of the redirect URI(s)
		 * allowing the ability to add, replace, or remove.
		 *
		 * @param redirectUrisConsumer a {@link Consumer} of the redirect URI(s)
		 * @return the {@link Builder}
		 */
		public Builder redirectUris(Consumer<Set<String>> redirectUrisConsumer) {
			redirectUrisConsumer.accept(this.redirectUris);
			return this;
		}

		/**
		 * Adds a scope the client may use.
		 *
		 * @param scope the scope
		 * @return the {@link Builder}
		 */
		public Builder scope(String scope) {
			this.scopes.add(scope);
			return this;
		}

		/**
		 * A {@code Consumer} of the scope(s)
		 * allowing the ability to add, replace, or remove.
		 *
		 * @param scopesConsumer a {@link Consumer} of the scope(s)
		 * @return the {@link Builder}
		 */
		public Builder scopes(Consumer<Set<String>> scopesConsumer) {
			scopesConsumer.accept(this.scopes);
			return this;
		}

		/**
		 * Sets the {@link ClientSettings client configuration settings}.
		 *
		 * @param clientSettings the client configuration settings
		 * @return the {@link Builder}
		 */
		public Builder clientSettings(ClientSettings clientSettings) {
			this.clientSettings = clientSettings;
			return this;
		}

		/**
		 * Sets the {@link TokenSettings token configuration settings}.
		 *
		 * @param tokenSettings the token configuration settings
		 * @return the {@link Builder}
		 */
		public Builder tokenSettings(TokenSettings tokenSettings) {
			this.tokenSettings = tokenSettings;
			return this;
		}

		/**
		 * Builds a new {@link RegisteredClient}.
		 *
		 * @return a {@link RegisteredClient}
		 */
		public RegisteredClient build() {
			Assert.hasText(this.clientId, "clientId cannot be empty");
			Assert.notEmpty(this.authorizationGrantTypes, "authorizationGrantTypes cannot be empty");
			if (this.authorizationGrantTypes.contains(AuthorizationGrantType.AUTHORIZATION_CODE)) {
				Assert.notEmpty(this.redirectUris, "redirectUris cannot be empty");
			}
			if (!StringUtils.hasText(this.clientName)) {
				this.clientName = this.id;
			}
			if (CollectionUtils.isEmpty(this.clientAuthenticationMethods)) {
				this.clientAuthenticationMethods.add(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
			}
			validateScopes();
			validateRedirectUris();
			return create();
		}

		private RegisteredClient create() {
			RegisteredClient registeredClient = new RegisteredClient();

			registeredClient.id = this.id;
			registeredClient.clientId = this.clientId;
			registeredClient.clientIdIssuedAt = this.clientIdIssuedAt;
			registeredClient.clientSecret = this.clientSecret;
			registeredClient.clientSecretExpiresAt = this.clientSecretExpiresAt;
			registeredClient.clientName = this.clientName;
			registeredClient.clientAuthenticationMethods = Collections.unmodifiableSet(
					new HashSet<>(this.clientAuthenticationMethods));
			registeredClient.authorizationGrantTypes = Collections.unmodifiableSet(
					new HashSet<>(this.authorizationGrantTypes));
			registeredClient.redirectUris = Collections.unmodifiableSet(
					new HashSet<>(this.redirectUris));
			registeredClient.scopes = Collections.unmodifiableSet(
					new HashSet<>(this.scopes));
			registeredClient.clientSettings = this.clientSettings != null ?
					this.clientSettings : ClientSettings.builder().build();
			registeredClient.tokenSettings = this.tokenSettings != null ?
					this.tokenSettings : TokenSettings.builder().build();

			return registeredClient;
		}

		private void validateScopes() {
			if (CollectionUtils.isEmpty(this.scopes)) {
				return;
			}

			for (String scope : this.scopes) {
				Assert.isTrue(validateScope(scope), "scope \"" + scope + "\" contains invalid characters");
			}
		}

		private static boolean validateScope(String scope) {
			return scope == null ||
					scope.chars().allMatch(c -> withinTheRangeOf(c, 0x21, 0x21) ||
							withinTheRangeOf(c, 0x23, 0x5B) ||
							withinTheRangeOf(c, 0x5D, 0x7E));
		}

		private static boolean withinTheRangeOf(int c, int min, int max) {
			return c >= min && c <= max;
		}

		private void validateRedirectUris() {
			if (CollectionUtils.isEmpty(this.redirectUris)) {
				return;
			}

			for (String redirectUri : redirectUris) {
				Assert.isTrue(validateRedirectUri(redirectUri),
						"redirect_uri \"" + redirectUri + "\" is not a valid redirect URI or contains fragment");
			}
		}

		private static boolean validateRedirectUri(String redirectUri) {
			try {
				URI validRedirectUri = new URI(redirectUri);
				return validRedirectUri.getFragment() == null;
			} catch (URISyntaxException ex) {
				return false;
			}
		}
	}
}
