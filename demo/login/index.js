const express = require('express');
const jwt = require('jsonwebtoken');

const app = express();

app.get('/login', (req, res) => {
    const token = jwt.sign(
        {
            iss: 'm2m-client-id',
            exp: Math.floor(Date.now() / 1000) + (60 * 60), // 1h
        },
        'super-secret-key',
        {
            algorithm: 'HS256',
            header: { kid: 'my-m2m-key' },
        }
    );

    res.json({ token });
});

app.get('/verify', (req, res) => {
    const token = req.headers.authorization.split(' ')[1];

    jwt.verify(token, 'super-secret-key', (err, decoded) => {
        if (err) {
            res.status(401).json({ error: 'Invalid token' });
            return;
        }

        res.json(decoded);
    });
});

app.listen(3000, () => {
    console.log('M2M login service running on port 3000');
});