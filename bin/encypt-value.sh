pass=$1
value=$2
mvn jasypt:encrypt-value \
-Djasypt.encryptor.password="$pass" \
-Djasypt.plugin.value="$value"