<?php

try {
	$payload = json_decode(file_get_contents('php://input'));
} catch(Exception $e) {
	exit(0);
}

// log the request
file_put_contents('logs/github.txt', print_r($payload, TRUE), FILE_APPEND);

if ($payload->ref === 'refs/heads/master') {
	echo 'deploying from github', PHP_EOL;

	$message = shell_exec("/var/www/html/deploy_wrapper 2>&1");
	print_r($message);
}

echo 'git_deploy.php done!', PHP_EOL;
